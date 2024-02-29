package br.com.reserva.reservasystem.enums;

public enum Status {
    AGUARDANDO_APROVACAO("aguardando"),
    APROVADO("aprovado"),
    REJEITADO("rejeitado");

    private String status;

    Status(String status){
        this.status = status;
    }

    public static Status fromString(String input){
        for(Status status : Status.values()){
            if(status.status.equalsIgnoreCase(input)){
                return status;
            }
        }
        throw new IllegalArgumentException("Nenhum status encontrado para a busca fornecida: " + input);
    }
}
