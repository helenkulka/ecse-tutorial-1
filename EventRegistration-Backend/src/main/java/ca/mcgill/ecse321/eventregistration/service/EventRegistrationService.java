package ca.mcgill.ecse321.eventregistration.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.eventregistration.dao.*;
import ca.mcgill.ecse321.eventregistration.model.*;

@Service
public class EventRegistrationService {

	@Autowired
	private EventRepository eventRepository;
	@Autowired
	private PersonRepository personRepository;
	@Autowired
	private RegistrationRepository registrationRepository;

	////added respositories
	@Autowired
	private TheatreRepository theatreRepository;
	@Autowired 
	private OrganizerRepository organizerRepository;
	@Autowired
	private CreditCardRepository creditCardRepository;


	@Transactional
	public Person createPerson(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Person name cannot be empty!");
		} else if (personRepository.existsById(name)) {
			throw new IllegalArgumentException("Person has already been created!");
		}
		Person person = new Person();
		person.setName(name);
		personRepository.save(person);
		return person;
	}


	@Transactional
	public Person getPerson(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Person name cannot be empty!");
		}
		Person person = personRepository.findByName(name);
		return person;
	}

	@Transactional
	public List<Person> getAllPersons() {
		return toList(personRepository.findAll());
	}

	@Transactional
	public Event buildEvent(Event event, String name, Date date, Time startTime, Time endTime) {
		// Input validation
		String error = "";
		if (name == null || name.trim().length() == 0) {
			error = error + "Event name cannot be empty! ";
		} else if (eventRepository.existsById(name)) {
			throw new IllegalArgumentException("Event has already been created!");
		}
		if (date == null) {
			error = error + "Event date cannot be empty! ";
		}
		if (startTime == null) {
			error = error + "Event start time cannot be empty! ";
		}
		if (endTime == null) {
			error = error + "Event end time cannot be empty! ";
		}
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			error = error + "Event end time cannot be before event start time!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		event.setName(name);
		event.setDate(date);
		event.setStartTime(startTime);
		event.setEndTime(endTime);
		return event;
	}

	@Transactional
	public Event createEvent(String name, Date date, Time startTime, Time endTime) {
		Event event = new Event();
		buildEvent(event, name, date, startTime, endTime);
		eventRepository.save(event);
		return event;
	}

	@Transactional
	public Event getEvent(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Event name cannot be empty!");
		}
		Event event = eventRepository.findByName(name);
		return event;
	}

	// This returns all objects of instance "Event" (Subclasses are filtered out)
	@Transactional
	public List<Event> getAllEvents() {
		return toList(eventRepository.findAll()).stream().filter(e -> e.getClass().equals(Event.class)).collect(Collectors.toList());
	}

	@Transactional
	public Registration register(Person person, Event event) {
		String error = "";
		if (person == null) {
			error = error + "Person needs to be selected for registration! ";
		} else if (!personRepository.existsById(person.getName())) {
			error = error + "Person does not exist! ";
		}
		if (event == null) {
			error = error + "Event needs to be selected for registration!";
		} else if (!eventRepository.existsById(event.getName())) {
			error = error + "Event does not exist!";
		}
		if (registrationRepository.existsByPersonAndEvent(person, event)) {
			error = error + "Person is already registered to this event!";
		}

		error = error.trim();

		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}

		Registration registration = new Registration();
		registration.setId(person.getName().hashCode() * event.getName().hashCode());
		registration.setPerson(person);
		registration.setEvent(event);

		registrationRepository.save(registration);

		return registration;
	}

	@Transactional
	public List<Registration> getAllRegistrations() {
		return toList(registrationRepository.findAll());
	}

	@Transactional
	public Registration getRegistrationByPersonAndEvent(Person person, Event event) {
		if (person == null || event == null) {
			throw new IllegalArgumentException("Person or Event cannot be null!");
		}

		return registrationRepository.findByPersonAndEvent(person, event);
	}
	@Transactional
	public List<Registration> getRegistrationsForPerson(Person person){
		if(person == null) {
			throw new IllegalArgumentException("Person cannot be null!");
		}
		return registrationRepository.findByPerson(person);
	}

	@Transactional
	public List<Registration> getRegistrationsByPerson(Person person) {
		return toList(registrationRepository.findByPerson(person));
	}

	@Transactional
	public List<Event> getEventsAttendedByPerson(Person person) {
		if (person == null) {
			throw new IllegalArgumentException("Person cannot be null!");
		}
		List<Event> eventsAttendedByPerson = new ArrayList<>();
		for (Registration r : registrationRepository.findByPerson(person)) {
			eventsAttendedByPerson.add(r.getEvent());
		}
		return eventsAttendedByPerson;
	}

	private <T> List<T> toList(Iterable<T> iterable) {
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}

	/////THEATRE CLASSES///////
	@Transactional
	public Theatre buildTheatre(String title, Theatre theatre, String name, Date date, Time startTime, Time endTime) {
		// Input validation
		String error = "";
		if (title == null || title.trim().length() == 0) {
			error = error + "Theatre title cannot be empty! ";
		}
		if (name == null || name.trim().length() == 0) {
			error = error + "Event name cannot be empty! ";
		} else if (eventRepository.existsById(name)) {
			throw new IllegalArgumentException("Event has already been created!");
		}
		if (date == null) {
			error = error + "Event date cannot be empty! ";
		}
		if (startTime == null) {
			error = error + "Event start time cannot be empty! ";
		}
		if (endTime == null) {
			error = error + "Event end time cannot be empty! ";
		}
		if (endTime != null && startTime != null && endTime.before(startTime)) {
			error = error + "Event end time cannot be before event start time!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		theatre.setName(name);
		theatre.setDate(date);
		theatre.setStartTime(startTime);
		theatre.setEndTime(endTime);
		theatre.setTitle(title);
		return theatre;
	}

	@Transactional
	public Theatre createTheatre(String name, Date date, Time startTime, Time endTime, String title) {
		Theatre theatre = new Theatre();
		theatre = buildTheatre(title, theatre, name, date, startTime, endTime);
		theatreRepository.save(theatre);
		return theatre;
	}

	@Transactional
	public List<Theatre> getAllTheatres() {
		return theatreRepository.findAllTheatres();
	}

	/////CREDIT CARD CLASS///////
	@Transactional
	public CreditCard createCreditCardPay(String id, int amount) {
		String error = "";
		if (id == null || id.trim().length() == 0 || id.length() != 8 || id.charAt(4) != '-' || !Character.isDigit(id.charAt(0)) || !Character.isDigit(id.charAt(1)) || !Character.isDigit(id.charAt(2)) || !Character.isDigit(id.charAt(3))
		|| !Character.isDigit(id.charAt(5)) || !Character.isDigit(id.charAt(6)) || !Character.isDigit(id.charAt(7)) || !Character.isDigit(id.charAt(8))) {
			error = error + "Account number is null or has wrong format!";
		}
		if (amount < 0) {
			error = error + "Payment amount cannot be negative!";
		}
		error = error.trim();
		if (error.length() > 0) {
			throw new IllegalArgumentException(error);
		}
		CreditCard creditCard = new CreditCard();
		creditCard.setAccountNumber(id);
		creditCard.setAmount(amount);
		creditCardRepository.save(creditCard);
		return creditCard;
	}

	@Transactional
	public void pay(Registration r, CreditCard c) {
		if (r == null || c == null) {
			throw new IllegalArgumentException("Registration and payment cannot be null!");
		} 

		r.setCreditCard(c);
	}

	/////ORGANIZERS CLASSES/////////
	@Transactional 
	public Organizer createOrganizer(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Organizer name cannot be empty!");
		} else if (organizerRepository.existsById(name)) {
			throw new IllegalArgumentException("Organizer has already been created!");
		}
		Organizer organizer = new Organizer();
		organizer.setName(name);
		organizerRepository.save(organizer);
		return organizer;
	}

	@Transactional
	public List<Organizer> getAllOrganizers() {
		return organizerRepository.findAllOrganizers();
	}

	@Transactional
	public void organizesEvent(Organizer organizer, Event event) {
		if (organizer == null) {
			throw new IllegalArgumentException("Organizer needs to be selected for organizes!");
		} 
		if (event == null) {
			throw new IllegalArgumentException("Event does not exist!");
		}

		organizer.setOrganizes(event);
	}

	@Transactional
	public Organizer getOrganizer(String name) {
		if (name == null || name.trim().length() == 0) {
			throw new IllegalArgumentException("Person name cannot be empty!");
		}
		return organizerRepository.findByName(name);


	}

}
