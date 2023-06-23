package com.Calendrify.Calendrify.Services;

import com.Calendrify.Calendrify.Healpers.Handlers.ResponseHandler;
import com.Calendrify.Calendrify.Models.Event;
import com.Calendrify.Calendrify.Models.GroupWithUsers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@Service
@SuppressWarnings("unchecked")
public class EventReminderService {
    @Autowired
    EventService eventService;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Scheduled(cron = "0 */5 * * * *")
    public void CheckEventTime() {
        System.out.println("CheckEventTime");
        ResponseEntity<ResponseHandler> response = eventService.getAllEvents(null, null, null, null);
        Map<String, Object> responseBody = (Map<String, Object>) response.getBody();
        if (responseBody != null) {
            List<Event> eventList = (List<Event>) responseBody.get("data");
            if (!eventList.isEmpty()) {
                LocalDateTime currentDateTime = LocalDateTime.now();
                for (Event event : eventList) {
                    LocalDate dateToCheck = event.getStartDateTime().toLocalDate();
                    LocalDate currentDate = LocalDate.now();
                    if (currentDate.isEqual(dateToCheck)) {
                        Duration duration = Duration.between(currentDateTime, event.getStartDateTime());
                        long minutesDifference = duration.toMinutes();
                        if(minutesDifference<=5&& minutesDifference>=0){
                            String message=minutesDifference==0?"":""+minutesDifference+" Minutes remaining for ";
                            event.setTitle("Reminder :"+message+event.getTitle());
                            eventService.sendEventNotification(event,event.getTitle());
                        }
                    }
                }
            }
        }
    }
}
