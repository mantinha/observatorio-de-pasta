package com.mantinha.observatoriodepasta.watcher;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class Sentinel {
	
	public void watcher() throws IOException, InterruptedException {
		
//		final String RESOURCE = new ClassPathResource("").getURL().getPath();
		final String PATH = "C:\\Users\\adriano\\projetos\\observatorio-de-pasta\\src\\main\\resources";
		WatchService watchService = FileSystems.getDefault().newWatchService();
	    Path path = Paths.get(PATH);
	    
	    path.register(watchService, StandardWatchEventKinds.ENTRY_CREATE, StandardWatchEventKinds.ENTRY_DELETE, StandardWatchEventKinds.ENTRY_MODIFY);
	    
	    WatchKey key;
	    
	    while ((key = watchService.take()) != null) {
	        for (WatchEvent<?> event : key.pollEvents()) {
	            System.out.println("Event kind:" + event.kind() + ". File affected: " + event.context() + ".");
	        }
	        key.reset();
	    }

	    watchService.close();
	}
}
