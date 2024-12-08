package com.algoriddle.AlgoRiddleBackendApi.Entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TestCase {
    @Id
    @Column(name = "testCase_ID")
    @GeneratedValue(strategy = GenerationType.UUID)
    public @Getter @Setter UUID ID;
    public @Getter @Setter String name;
    public @Getter @Setter String code;

    public String getMethodName(){
        // Define a regular expression pattern to match the method name
        String regex = "^\\s*def\\s+(\\w+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(this.code);

        // If the pattern matches, extract the method name
        if (matcher.find()) {
            return matcher.group(1);
        }

        // Return null if method name is not found
        return null;
    }
}
