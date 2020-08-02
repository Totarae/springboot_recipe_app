package pckg.su.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pckg.su.commands.NoteCommand;
import pckg.su.domains.Note;

@Component
public class NotesCommandToNotes implements Converter<NoteCommand, Note> {
    @Synchronized
    @Nullable
    @Override
    public Note convert(NoteCommand source) {
        if(source == null)
            return null;

        final Note notes = new Note();
        notes.setId(source.getId());
        notes.setRecipeNotes(source.getRecipeNotes());
        return notes;
    }
}
