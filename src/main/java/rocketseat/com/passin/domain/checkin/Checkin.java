package rocketseat.com.passin.domain.checkin;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import rocketseat.com.passin.domain.attendee.Attendee;

@Entity
@Data
@Table(name="check_ins")
public class Checkin {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private LocalDateTime createdAt;
	
	@OneToOne
	@JoinColumn(name="attendee_id")
	private Attendee attendee;
}
