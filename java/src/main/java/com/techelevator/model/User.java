package com.techelevator.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.techelevator.security.model.Authority;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class User {
   //security
   private int id;
   @NotNull
   private String username;

   @NotNull
   private String firstName;
   @NotNull
   private String lastName;

   //security
   @NotNull
   @JsonIgnore
   private String password;
   @JsonIgnore
   private boolean activated;
   private String role;

   @JsonIgnore
   private Set<Authority> authorities = new HashSet<>();

   private String email;
   private String phoneNumber;
   private String badge;
   private List<Vehicle> vehicles;
   private List<WorkOrder> workOrders;
   private List<Invoice> invoices;



   private String fullName(){
      return firstName + " " + lastName;
   }

   private String getBadge(){
      if (invoices == null || invoices.size() < 3) {
         return "";
      }else if (invoices.size() >= 9) {
         return "Gold";
      } else if (invoices.size() >= 6) {
         return "Silver:";
      } else {
         return "Bronze";
      }
   }

   public User() {

   }

   public User(int id, String username, String firstName, String lastName, String password,
               String authorities, String email, String phoneNumber) {
      this.id = id;
      this.username = username;
      this.firstName = firstName;
      this.lastName = lastName;
      this.password = password;
      if (authorities != null) this.setAuthorities(authorities);
      this.activated = true;
      this.email = email;
      this.phoneNumber = phoneNumber;
      this.badge = getBadge();

   }

   public User(int id, String username, String firstName, String lastName, String authorities,
               String password, String email, String phoneNumber,
               List<Vehicle> vehicles, List<WorkOrder> workOrders, List<Invoice> invoices) {
      this.id = id;
      this.username = username;
      this.firstName = firstName;
      this.lastName = lastName;
      this.password = password;
      if (authorities != null) this.setAuthorities(authorities);
      this.activated = true;
      this.email = email;
      this.phoneNumber = phoneNumber;
      this.badge = getBadge();
      this.vehicles = vehicles;
      this.workOrders = workOrders;
      this.invoices = invoices;
   }

   public User(int id, String username, String password, String authorities) {
      this.id = id;
      this.username = username;
      this.password = password;
      if (authorities != null) this.setAuthorities(authorities);
      this.activated = true;
   }


   

   public int getId() {
      return id;
   }

   public void setId(int id) {
      this.id = id;
   }

   public String getUsername() {
      return username;
   }

   public void setUsername(String username) {
      this.username = username;
   }

   public String getPassword() {
      return password;
   }

   public void setPassword(String password) {
      this.password = password;
   }

   public boolean isActivated() {
      return activated;
   }

   public void setActivated(boolean activated) {
      this.activated = activated;
   }

   public Set<Authority> getAuthorities() {
      return authorities;
   }
   public void setAuthorities(String authorities) {
      String[] roles = authorities.split(",");
      for (String role : roles) {
         String authority = role.contains("ROLE_") ? role : "ROLE_" + role;
         this.authorities.add(new Authority(authority));
      }
   }

   public void setAuthorities(Set<Authority> authorities) {
      this.authorities = authorities;
   }

   public String getFirstName() {
      return firstName;
   }

   public void setFirstName(String firstName) {
      this.firstName = firstName;
   }

   public String getLastName() {
      return lastName;
   }

   public void setLastName(String lastName) {
      this.lastName = lastName;
   }


   public String getEmail() {
      return email;
   }

   public void setEmail(String email) {
      this.email = email;
   }

   public String getPhoneNumber() {
      return phoneNumber;
   }

   public void setPhoneNumber(String phoneNumber) {
      this.phoneNumber = phoneNumber;
   }

   public List<Vehicle> getVehicles() {
      return vehicles;
   }

   public void setVehicles(List<Vehicle> vehicles) {
      this.vehicles = vehicles;
   }

   public List<WorkOrder> getWorkOrders() {
      return workOrders;
   }

   public void setWorkOrders(List<WorkOrder> workOrders) {
      this.workOrders = workOrders;
   }

   public List<Invoice> getInvoices() {
      return invoices;
   }

   public void setInvoices(List<Invoice> invoices) {
      this.invoices = invoices;
   }

   public String getRole() {
      return role;
   }

   public void setRole(String role) {
      this.role = role;
   }

   public void setBadge(String badge) {
      this.badge = badge;
   }

   @Override
   public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      User user = (User) o;
      return id == user.id &&
              activated == user.activated &&
              Objects.equals(username, user.username) &&
              Objects.equals(password, user.password) &&
              Objects.equals(authorities, user.authorities);
   }

   @Override
   public int hashCode() {
      return Objects.hash(id, username, password, activated, authorities);
   }

   @Override
   public String toString() {
      return "User{" +
              "id=" + id +
              ", username='" + username + '\'' +
              ", activated=" + activated +
              ", authorities=" + authorities +
              '}';
   }
}
