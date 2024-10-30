package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class TextEditor {
    private final TextWriter textWriter;

    @Autowired
    public TextEditor(@Qualifier("plainTextWriter") TextWriter textWriter) {
        this.textWriter = textWriter;
    }

    public void input() {
        textWriter.input();
    }

    public void save() {
        textWriter.save();
    }
}
