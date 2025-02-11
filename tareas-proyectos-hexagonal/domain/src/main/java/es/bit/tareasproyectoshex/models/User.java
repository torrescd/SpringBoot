package es.bit.tareasproyectoshex.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    private long uid;

    @NonNull
    private String name;

    private String email;
    
    private String password;
}
