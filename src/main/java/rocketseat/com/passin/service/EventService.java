package rocketseat.com.passin.service;

import rocketseat.com.passin.domain.attendee.Attendee;
import rocketseat.com.passin.domain.event.Event;
import rocketseat.com.passin.dto.event.EventIdDTO;
import rocketseat.com.passin.dto.event.EventRequestDTO;
import rocketseat.com.passin.dto.event.EventResponseDTO;
import rocketseat.com.passin.repository.AttendeeRepository;
import rocketseat.com.passin.repository.EventRepository;

import java.text.Normalizer;
import java.util.List;

import org.springframework.stereotype.Service;


import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EventService {
	
	
	private final EventRepository eventRepository;
	private final AttendeeRepository attendeeRepository;
	
	public EventResponseDTO getEventDetail(String eventId) {
	Event event = this.eventRepository.findById(eventId).orElseThrow(() -> new RuntimeException("Event not found with ID: " + eventId));
	List<Attendee> attendeeList = this.attendeeRepository.findByEventId(eventId);
	return new EventResponseDTO(event, attendeeList.size());
	}
	
	
	public EventIdDTO createEvent(EventRequestDTO eventDTO) {
		Event newEvent = new Event();
		
		newEvent.setTitle(eventDTO.title());
		newEvent.setDetails(eventDTO.details());
		newEvent.setMaximumAttendees(eventDTO.maximumAttendees());
		newEvent.setSlug(this.createSlug(eventDTO.title()));
		
		this.eventRepository.save(newEvent);
		
		return new EventIdDTO(newEvent.getId());
		
	}
	
	// Separa os acentos dos caracteres
	// Remove os acentos
	// Substitui espaços em branco por ifens
	// normaliza todos os caracteres em letras minúsculas
	private String createSlug(String text) {
		String normalized = Normalizer.normalize(text, Normalizer.Form.NFD);
		return normalized.replaceAll("[\\p{InCOMBINING_DIACRITICAL_MARKS}]", "")
				.replaceAll("[^\\w\\s]", "")
				.replaceAll("\\s+", "-")
				.toLowerCase();
	}

}