package br.com.reserva.reservasystem.enums;

public enum DayOfWeek {
        MONDAY("Segunda-feira"),
        TUESDAY("Terça-feira"),
        WEDNESDAY("Quarta-feira"),
        THURSDAY("Quinta-feira"),
        FRIDAY("Sexta-feira"),
        SATURDAY("Sábado"),
        SUNDAY("Domingo");

        private String daySet;

        DayOfWeek(String day){
                this.daySet = day;
        }

        public static DayOfWeek fromString(String dayWeek){
                for(DayOfWeek day : DayOfWeek.values()){
                        if(day.daySet.equalsIgnoreCase(dayWeek)){
                                return day;
                        }
                }
                throw new IllegalArgumentException();
        }
}
