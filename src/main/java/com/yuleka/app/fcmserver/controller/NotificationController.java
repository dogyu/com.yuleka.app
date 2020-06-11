package com.yuleka.app.fcmserver.controller;

import java.nio.charset.Charset;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.json.JSONException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import com.yuleka.app.fcmserver.service.AndroidPushNotificationsService;
import com.yuleka.app.fcmserver.service.AndroidPushPeriodicNotifications;


@RestController
public class NotificationController {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    AndroidPushNotificationsService androidPushNotificationsService;

    @GetMapping(value = "/send")
    public @ResponseBody
    ResponseEntity<String> send() throws JSONException, InterruptedException  {
        String notifications = AndroidPushPeriodicNotifications.PeriodicNotificationJson();

        HttpHeaders headers = new HttpHeaders();

        Charset utf8 = Charset.forName("UTF-8");

        MediaType mediaType = new MediaType("application", "json", utf8);

        headers.setContentType(mediaType);

        HttpEntity<String> request = new HttpEntity<>(notifications, headers);

        CompletableFuture<String> pushNotification = androidPushNotificationsService.send(request);
        CompletableFuture.allOf(pushNotification).join();

        try{
            String firebaseResponse = pushNotification.get();
            return new ResponseEntity<>(firebaseResponse, HttpStatus.OK);
        }
        catch (InterruptedException e){
            logger.debug("got interrupted!");
            throw new InterruptedException();
        }
        catch (ExecutionException e){
            logger.debug("execution error!");
        }

        return new ResponseEntity<>("Push Notification ERROR!", HttpStatus.BAD_REQUEST);
    }
}
