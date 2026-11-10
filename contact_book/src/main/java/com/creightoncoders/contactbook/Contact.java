package com.creightoncoders.contactbook;

public class Contact {
        private String name;

        public Contact(String name){
            this.name = name;
        }

        public Contact(){
        }

        public String getName(){
            return this.name;
        }

        public void setName(String name){
            this.name = name;
        }
}
