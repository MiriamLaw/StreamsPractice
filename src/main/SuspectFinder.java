package main;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import main.domain.Suspect;
import main.service.FileService;

public class SuspectFinder {

	public static void main(String[] args) {
		FileService fileService = new FileService();
		List<Suspect> suspectLineUp = fileService.getSuspectsFromFile();
		
		
		// There should only be one person that is returned here...
		// The map listed here is the birth year (Integer) and the List of suspects
		// first filter out all the suspects with red hair...
		// then filter out all the suspects with glasses
		// then group by their birth year (Collectors.groupingBy(Suspect::getBirthYear, Collectors.toList())
		//   -> this will return the birth year and a list of suspect objects (if done correctly, there should
		//		only be one suspect object.
		
		
		Map<Integer, List<Suspect>> suspects = null;
		// APPLY FILTERING LOGIC HERE....
		List<Suspect> filteredSuspects = suspectLineUp.stream()
														.filter(suspect -> !suspect.getHairColor().equalsIgnoreCase("red"))
														.filter(suspect -> !suspect.getHasGlasses())
														.collect(Collectors.toList());
		
		Map<Integer, List<Suspect>>  suspectsByBirthYear = filteredSuspects.stream()
																			.collect(Collectors.groupingBy(Suspect::getBirthYear, Collectors.toList())
																	  );
																	  
															
		
		Map<Integer, List<Suspect>> shortList = suspectsByBirthYear;

		String guilty = shortList.entrySet()
								.stream()
								.map(entry -> entry.getValue().get(0).getName())
								.findFirst()
								.orElse(null);

		System.out.println(guilty);
		
	}

}






