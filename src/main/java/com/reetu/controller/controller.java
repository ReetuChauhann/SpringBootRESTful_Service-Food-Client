package com.reetu.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import com.reetu.beans.Food;

@Controller
public class controller {
	
	          RestTemplate rt= new RestTemplate();
	          String URL="http://localhost:9000";
	          
	          @RequestMapping("/")
	          public String home() {
	        	  return "index";
	          }
	          
	          @RequestMapping("/addfood")
	          public String addFood(@ModelAttribute Food f,@RequestPart("image") MultipartFile image, Model model) {
	        	 String API="/addFood";
	        	 
	        	 HttpHeaders header= new HttpHeaders();
	        	 header.setContentType(MediaType.MULTIPART_FORM_DATA);
	        	 
	        	 LinkedMultiValueMap<String, Object> data=new LinkedMultiValueMap<>();
	        	 data.add("image", convert(image));
	        	 data.add("Food", f);
	        	 
	        	 HttpEntity<LinkedMultiValueMap<String, Object>> requestEntity = new  HttpEntity<>(data,header);
	        	 ResponseEntity<String> xyz=rt.postForEntity(URL+API, requestEntity, String.class);
	        	 if(xyz.getStatusCode()==HttpStatus.OK) {
	        		 model.addAttribute("added", f.getName() +"Successfully added");
	        	 }else {
	        		 model.addAttribute("added", f.getName()+ "Allready exists!");
	        	 }
	        	 
	        	 return "index";
	        	 
	          }
	          
	          //convert multipart image to FileSystemResource
	          public static FileSystemResource convert(MultipartFile image) {
	        	  File convertedf= new File(image.getOriginalFilename());
	        	  try {
					      convertedf.createNewFile();
					      FileOutputStream fos= new FileOutputStream(convertedf);
					      fos.write(image.getBytes());
					      fos.close();
					      
				} catch (Exception e) {
					e.printStackTrace();
				}
	              return new FileSystemResource(convertedf);
	          }
	          
	         //get all data
	          @GetMapping("/getallfood")
	          public String getallfood(Model model){
	        	  String API="/getallFood";
	        	  List<Food> f=rt.getForObject(URL+API, List.class);
	        	  model.addAttribute("food", f);
	        	  return "getallfood";
	        		  
	        	  }
	          //get image
	          @GetMapping("/getimage")
	          public void getimage(int fid, HttpServletResponse response) {
	        	  String API="/getImage/"+fid;
	        	 try {
	        		 byte[] b=rt.getForObject(URL+API, byte[].class);
	        		 response.getOutputStream().write(b);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
	          }
	          
	          //update image
	          @RequestMapping("updateimage")
	          public String updateimage(int fid, @RequestPart("image") MultipartFile image, Model model) {
	        	   String API="/updateImage";
	        	  
	        	  HttpHeaders header = new HttpHeaders();
	        	  header.setContentType(MediaType.MULTIPART_FORM_DATA);
	        	  
	        	  LinkedMultiValueMap<String, Object> data=new LinkedMultiValueMap<String, Object>();
	        	  data.add("fid", fid);
	        	  data.add("image", convert(image));
	        	  
	        	  HttpEntity<LinkedMultiValueMap<String , Object>> requestEntity = new HttpEntity<>(data,header);
	        	  ResponseEntity<String> messg= rt.exchange(URL+API, HttpMethod.PUT, requestEntity, String.class);
	        	  if(messg.getStatusCode()==HttpStatus.OK) {
	        		  model.addAttribute("messg" , "Success");
	        	  }else {
	        		  model.addAttribute("messg" , "Failed");
	        	  }
	        	  
	        	  API="/getallFood";
	        	  List<Food> f=rt.getForObject(URL+API, List.class);
	        	  model.addAttribute("food", f);
	        	  return "getallfood";
	        	  
	          }

	          @ModelAttribute
	          public void getallides(Model model){
	        	  String API="/getallids";
	        	  
	        	  List<Integer> ids=rt.getForObject(URL+API, List.class);
	        	  model.addAttribute("ids",ids);
	          }
	          
	          @RequestMapping("/updatefood")  
	          public String returnpage() {
	        	  return "updateindex";
	          }

	          @RequestMapping("/Updatefood")
	          public String updatebyid(@ModelAttribute Food f, Model model) {
	        	  String API="/update";
	        	  HttpEntity<Food>  requestEntity = new HttpEntity<>(f);
	        	  ResponseEntity<String> status=rt.exchange(URL+API, HttpMethod.PUT, requestEntity, String.class);
	        	  String sts=status.getBody();
	        	  model.addAttribute("sts", sts);
	        	  return  "updateindex";
	        	  }
	          
 }
	
	    


