package ca.mcgill.ecse321.library.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.library.dao.NewspaperRepository;
import ca.mcgill.ecse321.library.model.Newspaper;


public class NewspaperService {
	
	@Autowired
	NewspaperRepository newspaperRepository;
	
	/**
	 * @author alymo
	 * Creates a newspaper
	 * @param date
	 * @param numberOfPages
	 * @param title
	 * @return
	 */
	@Transactional
	public Newspaper createnewspaper(String date, String numberOfPages, String title) {
		
		HelpersBooking.checkImmobileItemInfo(date, numberOfPages, title);		

		Newspaper newspaper = new Newspaper();
		newspaper.setDate(Date.valueOf(date));
		newspaper.setNumberOfPages(Integer.valueOf(numberOfPages));
		newspaper.setTitle(title);
		
		newspaperRepository.save(newspaper);
		
		return newspaper;
		
	}
	
	@Transactional
	public Newspaper getNewspaperByTitle(String title) {
		return newspaperRepository.findNewspaperByTitle(title);
	}
	
	@Transactional
	public boolean deletNewspaper(String title) {
		Newspaper newspaper = newspaperRepository.findNewspaperByTitle(title);
		if(newspaper!=null) {
			newspaperRepository.delete(newspaper);
			return true;
		}
		else return false;
	}
	
	public List<Newspaper> getAllnewspapers(){
		return HelpersBooking.toList(newspaperRepository.findAll());
	}
	
	
	
}