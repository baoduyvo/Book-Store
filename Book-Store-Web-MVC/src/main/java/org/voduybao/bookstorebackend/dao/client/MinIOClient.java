//package org.voduybao.bookstorebackend.dao.client;
//
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//@FeignClient(name = "mediaApp", url = "http://localhost:9001")
//@RequestMapping("/api/media")
//public interface MinIOClient {
//
//    @GetMapping(value = "/image", produces = MediaType.IMAGE_JPEG_VALUE)
//    @ResponseBody
//    byte[] getImage(@RequestParam("fileName") String fileName);
//}
