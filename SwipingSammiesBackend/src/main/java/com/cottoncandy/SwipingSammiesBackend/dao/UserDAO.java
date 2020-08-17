package com.cottoncandy.SwipingSammiesBackend.dao;
import com.cottoncandy.SwipingSammiesBackend.models.LikerId;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.transaction.annotation.Transactional;
import com.cottoncandy.SwipingSammiesBackend.models.User;
import com.cottoncandy.SwipingSammiesBackend.models.Likes;

import java.util.*;


@Transactional
@Repository
public class UserDAO {

    @PersistenceContext
    private EntityManager entityManager; //obj or class wrapped around mysql

    public User getUserByEmail(String email) {
        return entityManager.find(User.class, email);
    }

    public void registerUser(User user) {
        entityManager.persist(user);
    }

    public void updateUser(String email,  String name, String bio, String gender, String preferred_gender, int age){

        User user = entityManager.find(User.class, email);
        System.out.println(bio);
        user.setBio(bio);
        user.setName(name);
        user.setGender(gender);
        user.setPreferredGender(preferred_gender);
        user.setAge(age);
        entityManager.persist(user);
        //based on user that is provided find the user update that user
        //first finduser then update user

    }

    public List<String> getLikesByEmail(String email){
        List<String> likes = entityManager.createNativeQuery("SELECT LIKED FROM LIKES where LIKER = " + "\"" + email + "\"").getResultList();
        return likes;
    }

    public List<Map<String, String>> getMatchesByEmail(String email){


        List<Map<String, String>> matchedList = new ArrayList<>();

        List<String> matches = entityManager.createNativeQuery("SELECT LIKED FROM LIKES where LIKER = " + "\"" + email + "\" AND MATCHED = \"1\"").getResultList();

        matches.forEach((matchedEmail) -> {

            Map<String, String> matchedInfo = new HashMap<>();

            User tempUser = entityManager.find(User.class, matchedEmail);
            matchedInfo.put("email", tempUser.getEmail());
            matchedInfo.put("name",  tempUser.getName());
            matchedInfo.put("bio", tempUser.getBio());
            matchedInfo.put("gender" , tempUser.getGender());
            matchedInfo.put("preferredGender", tempUser.getPreferredGender());
            matchedInfo.put("age" , String.valueOf(tempUser.getAge()));
            matchedList.add(matchedInfo);
        });

        return matchedList;
    }

    public void makeLikes(String liker, String liked){

        boolean matchBoolean = false;
        List<String> likedByLiked = getLikesByEmail(liked);

        for(String personEmail : likedByLiked){
            System.out.println(personEmail);
            System.out.println(liker);

            if(personEmail.equals(liker)) {
                matchBoolean = true;
                System.out.println(matchBoolean);
            }
        }

        if(matchBoolean == true){
            LikerId matchId = new LikerId(liked, liker);
            Likes potentialMatch = entityManager.find(Likes.class, matchId);
            potentialMatch.setMatched(true);
            entityManager.persist(potentialMatch);
        }


        Likes like = new Likes(liker,liked, matchBoolean);
        entityManager.persist(like);

    }

    public boolean isMatched(String liker,String liked){

        LikerId likersId = new LikerId(liker, liked);
        Likes potentialMatch = entityManager.find(Likes.class, likersId);
        if(potentialMatch.getMatched() == true){
            return true;
        }

        return false;
    }


    public List<Map<String, String>> getPotentials(String email){

        List<Map<String, String>> returnedList = new ArrayList<>();

        User thisUser = entityManager.find(User.class, email);
        System.out.println(thisUser.getName());

        String preferredGender = thisUser.getPreferredGender();
        List<String> potentials = entityManager.createNativeQuery("SELECT EMAIL FROM USER WHERE GENDER = " + "\"" + preferredGender + "\" AND NOT EMAIL =" + "\"" + email + "\"").getResultList();

        potentials.forEach((matchedEmail) -> {

            Map<String, String> matchedInfo = new HashMap<>();

            User tempUser = entityManager.find(User.class, matchedEmail);
            matchedInfo.put("email", tempUser.getEmail());
            matchedInfo.put("name",  tempUser.getName());
            matchedInfo.put("bio", tempUser.getBio());
            matchedInfo.put("gender" , tempUser.getGender());
            matchedInfo.put("preferredGender", tempUser.getPreferredGender());
            matchedInfo.put("age" , String.valueOf(tempUser.getAge()));
            returnedList.add(matchedInfo);
        });

        return returnedList;
    }

  /* public void updateGender(String email, String gender){

        User user = entityManager.find(User.class, email);
        user.setGender(gender);
        entityManager.persist(user);
    }

    public void updatePreferredGender(String email, String gender){

        User user = entityManager.find(User.class, email);
        user.setGender(gender);
        entityManager.persist(user);
    }
    public void updateGender(String email, String gender){

        User user = entityManager.find(User.class, email);
        user.setGender(gender);
        entityManager.persist(user);
    }
    public void updateGender(String email, String gender){

        User user = entityManager.find(User.class, email);
        user.setGender(gender);
        entityManager.persist(user);
    }
*/
}


