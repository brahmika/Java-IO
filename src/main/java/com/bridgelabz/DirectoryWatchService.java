package com.bridgelabz;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.HashMap;
import java.util.Map;

public class DirectoryWatchService {

    private final WatchService watchService;
    private final Map<WatchKey, Path> keyMap = new HashMap<>();

    public DirectoryWatchService(Path dir) throws IOException {
        this.watchService = FileSystems.getDefault().newWatchService();
        registerAll(dir);
    }

    // Register directory and all subdirectories
    private void registerAll(final Path start) throws IOException {
        Files.walkFileTree(start, new SimpleFileVisitor() {
            public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs)
                    throws IOException {
                registerDirectory(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }

    private void registerDirectory(Path dir) throws IOException {
        WatchKey key = dir.register(
                watchService,
                StandardWatchEventKinds.ENTRY_CREATE,
                StandardWatchEventKinds.ENTRY_DELETE,
                StandardWatchEventKinds.ENTRY_MODIFY
        );
        keyMap.put(key, dir);
        System.out.println("Watching directory: " + dir);
    }

    public void processEvents() {
        System.out.println("Waiting for events...");

        while (true) {
            WatchKey key;
            try {
                key = watchService.take();
            } catch (InterruptedException e) {
                return;
            }

            Path dir = keyMap.get(key);
            if (dir == null) continue;

            for (WatchEvent<?> event : key.pollEvents()) {
                WatchEvent.Kind<?> kind = event.kind();

                Path name = (Path) event.context();
                Path child = dir.resolve(name);

                System.out.println(kind.name() + ": " + child);

                // If new directory created, register it
                if (kind == StandardWatchEventKinds.ENTRY_CREATE) {
                    try {
                        if (Files.isDirectory(child)) {
                            registerAll(child);
                        }
                    } catch (IOException ignored) {}
                }

                // If file modified, count entries (lines)
                if (kind == StandardWatchEventKinds.ENTRY_MODIFY &&
                        Files.isRegularFile(child)) {
                    countFileEntries(child);
                }
            }

            boolean valid = key.reset();
            if (!valid) {
                keyMap.remove(key);
                if (keyMap.isEmpty()) break;
            }
        }
    }

    private void countFileEntries(Path file) {
        try {
            long lineCount = Files.lines(file).count();
            System.out.println("File: " + file.getFileName()
                    + " has " + lineCount + " entries (lines).");
        } catch (IOException e) {
            System.out.println("Could not count entries.");
        }
    }

    public static void main(String[] args) throws IOException {

        Path dirToWatch = Paths.get("WatchDirectory");

        if (!Files.exists(dirToWatch)) {
            Files.createDirectories(dirToWatch);
        }

        DirectoryWatchService watcher =
                new DirectoryWatchService(dirToWatch);

        watcher.processEvents();
    }
}