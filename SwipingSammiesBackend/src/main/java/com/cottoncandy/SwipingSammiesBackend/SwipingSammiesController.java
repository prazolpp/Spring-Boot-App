package com.cottoncandy.SwipingSammiesBackend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.cottoncandy.SwipingSammiesBackend.dao.UserDAO;
import com.cottoncandy.SwipingSammiesBackend.models.User;
import com.cottoncandy.SwipingSammiesBackend.models.Likes;
import java.util.List;
import java.util.Map;

@Controller // This means that this class is a Controller
@RequestMapping(path="/") // This means URL's start with /demo (after Application path)
public class SwipingSammiesController {
    @Autowired // This means to get the bean called userRepository
    private UserDAO userDAO;

    @GetMapping(path="/hello")
    public @ResponseBody String hello() {
        return "Hello World";
    }

    public boolean validateEmail(String email){
        if(email.endsWith("@sjsu.edu")){
            return true;
        }
        return false;
    }

    @PostMapping(path="/user/login")
    public @ResponseBody String authenticateUser (@RequestBody User userRequest) {
        User foundUser = userDAO.getUserByEmail(userRequest.getEmail());
        if (foundUser != null && foundUser.getPassword().equals(userRequest.getPassword())) {
            return "Successfully logged in " + userRequest.getEmail();
        } else {
            return "Incorrect email or password";
        }
    }

    @PostMapping(path="/user/register")
    public @ResponseBody String registerUser (@RequestBody User userRequest) {

        try {
            if(userRequest.getEmail().endsWith("@sjsu.edu") && userRequest.getPassword() != null){
                userDAO.registerUser(userRequest);
                System.out.println(userRequest);
            }
            else{
                return "Invalid email ID. Please enter a SJSU Email Address.";
            }
        }
        catch(Exception e) {
            System.out.println(e);
            return "Failed to register user";
        }
        return "Successfully registered " + userRequest.getEmail();
    }


    @GetMapping(path="/user/info")
    public @ResponseBody User getUserInfo(@RequestBody User userRequest){

        User foundUser =  userDAO.getUserByEmail(userRequest.getEmail());
        if(foundUser == null){
            System.out.println("Usr Not found");
        }
        foundUser.setPassword(null);
        return foundUser;
    }


    @PutMapping(path="/user/info")
    public @ResponseBody String updateUser(@RequestBody User userRequest){

        try{
            userDAO.updateUser(userRequest.getEmail().toLowerCase(), userRequest.getName(), userRequest.getBio(), userRequest.getGender().toLowerCase(), userRequest.getPreferredGender().toLowerCase(), userRequest.getAge());
            System.out.println(userRequest.getBio());
            return "Succeeded to update User Info for User: " + userRequest.getName();
        }
        catch(Exception e){

            System.out.println(e);
            return "Failed to update info for User: " + userRequest.getName();
        }
    }

    @PostMapping(path="/user/likes")
    public @ResponseBody String setLikes(@RequestBody Likes like){

        try {

            if(userDAO.getUserByEmail(like.getLiker()) != null  && userDAO.getUserByEmail(like.getLiked()) != null){
                userDAO.makeLikes(like.getLiker(), like.getLiked());
            }
            else{
                return "Invalid Email or Emails";
            }

        }
        catch(Exception e){
            System.out.println(e);
        }
        return "The Like table has been updated";
    }


    @GetMapping(path="/user/likes")
    public @ResponseBody List<String> getLikes(@RequestBody User user){

        if(userDAO.getUserByEmail(user.getEmail()) != null){
            List<String> LikedValues = userDAO.getLikesByEmail(user.getEmail());
            return LikedValues;
        }

        System.out.println("The user emails are not in the database");
        return null;
    }

    @GetMapping(path="/user/matches")

    public @ResponseBody List<Map<String,String>> getMatches(@RequestBody User userRequest){

        if(userDAO.getUserByEmail(userRequest.getEmail()) != null){
            List<Map<String, String>> matches = userDAO.getPotentials(userRequest.getEmail());
            return matches;
        }

        System.out.println("The user email is not in the database");
        return null;
    }

    @GetMapping(path="/user/potential")
    public @ResponseBody List<Map<String, String>> getPotentials(@RequestBody User userRequest){

        if(userDAO.getUserByEmail(userRequest.getEmail()) != null){
            List<Map<String, String>> potentials = userDAO.getPotentials(userRequest.getEmail());
            return potentials;
        }

        System.out.println("The user email is not in the database");
        return null;
    }

    @GetMapping(path="/user/isMatched")
    public @ResponseBody Boolean isMatched(@RequestBody Likes likes){
        return userDAO.isMatched(likes.getLiker(), likes.getLiked());
    }



    /*
    To add info, use /user/bio with your url, and put all of the values in your request like:
        {
            "email" : "email@default.com",
            "name" : "Prajwal Pyakurel",
            "bio" : "nothing ",
            "gender" : "male",
            "preferred_gender" : "female",
            "age" : "22"
    }



      If you want to update one of the values, you can can update that and make a put request including other values that you are not changing.
    For example. if you want to update bio of the above user, only change bio and make request. If you want multiple changes add all changes.
    {
            "email" : "email@default.com",
            "name" : "Prajwal Pyakurel",
            "bio" : "I have added a new bio here to update my bio, email is the same other values can change",
            "gender" : "male",
            "preferred_gender" : "female",
            "age" : "22"
    }




    */

   /*

    @PutMapping(path = "/user/name")
    public @ResponseBody String updateName(@RequestBody User userRequest){

        try{
            userDAO.updateName(userRequest.getEmail(), userRequest.getName());
            System.out.println(userRequest.getName());
            return "Succeeded to update User Name for User: " + userRequest.getName();
        }
        catch(Exception e){
            System.out.println(e);
            return "Failed to update info for User: " + userRequest.getName();
        }


    }


    //rows
    //assumes mapping
    //pass down something as parameter
    //request body: JSON
    //pass as Json
    //

    //When someone tries to call backend, it reacts to path
*/
}
