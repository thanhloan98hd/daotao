package demoauthen.controller;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource; //Dùng cho DataSource

import org.springframework.beans.factory.annotation.Autowired; //Dùng cho @Autowired
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;//Dùng cho ResponseEntity
import org.springframework.stereotype.Controller; //Dùng cho @Controller
import org.springframework.web.bind.annotation.RequestBody;//Dùng cho @RequestBody
import org.springframework.web.bind.annotation.RequestMapping; //Dùng cho @Controller
import org.springframework.web.bind.annotation.RequestMethod; //Dùng cho RequestMethod



@Controller
public class HelloController{

	@Autowired
    private DataSource dataSource;
	
	@RequestMapping(value = "/hello", method = RequestMethod.GET)
	
	public ResponseEntity<String> processData(@RequestBody String request) {
		try (
				Connection conn = dataSource.getConnection();
	            CallableStatement stmt = conn.prepareCall("{call trungapiservice.trunginsert(?)}")
	        ) {
			
	            stmt.setString(1, request); //set value cho ? thứ nhất
	            
	            stmt.execute(); //thực thi câu lệnh
	            
	            return new ResponseEntity<>("Hoan thanh", HttpStatus.OK);
	        } catch (SQLException e) {
	            e.printStackTrace();
	            return new ResponseEntity<>("Loi thuc hien", HttpStatus.NOT_FOUND);
	        }
	}

}


