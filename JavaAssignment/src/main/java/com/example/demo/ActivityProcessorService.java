package com.example.demo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.annotation.PostConstruct;

@Service
public class ActivityProcessorService {

	 @Autowired
	    private ActivityFileRepository activityFileRepository;
	
    private static final String[] VALID_NAMES = {"doubleTap", "singleTap", "crash", "anr"};
    private static final String DIRECTORY_PATH = "D:/Akshada Documents/Activities To Process";

    @PostConstruct
    public void processFiles() {
        File directory = new File(DIRECTORY_PATH);
        
        

        try {
            //File[] files = directory.listFiles((dir, name) -> name.endsWith(".json"));
             File[] files = directory.listFiles();
            if (files != null) {
            	ObjectMapper objectMapper = new ObjectMapper();
                objectMapper.registerModule(new JavaTimeModule());
                objectMapper.registerModule(new SimpleModule());
                for (File file : files) {
                	ActivityFile activityFile = objectMapper.readValue(file, ActivityFile.class);
                    List<Activity> validActivities = validateActivities(activityFile.getActivities());
                    activityFile.setActivities(validActivities);
                    activityFileRepository.save(activityFile);
					/*
					 * validateAndCleanActivities(activityFile);
					 * validActivityFiles.add(activityFile);
					 */
                }
                //activityFileRepository.saveAll(validActivityFiles);
                System.out.println("saved inside database"+activityFileRepository.count());
                
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	/*
	 * private void validateAndCleanActivities(ActivityFile activityFile) {
	 * activityFile.getActivities().removeIf(activity ->
	 * !isValidActivity(activity)); }
	 */
	/*
	 * private boolean isValidActivity(Activity activity) { for (String validName :
	 * VALID_NAMES) { if (validName.equals(activity.getName())) { return true; } }
	 * return false; }
	 */
    
    private List<Activity> validateActivities(List<Activity> activities) {
    	 List<String> validNames = Arrays.asList("doubleTap", "singleTap", "crash", "anr");

         // Filter activities to include only valid ones
         return activities.stream()
                 .filter(activity -> validNames.contains(activity.getName()))
                 .collect(Collectors.toList());
    }
}
