package pckg.su.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import pckg.su.commands.NoteCommand;
import pckg.su.domains.Note;

@Component
public class NotesToNotesCommand implements Converter<Note, NoteCommand> {
    @Synchronized
    @Nullable
    @Override
    public NoteCommand convert(Note source) {
        if(source == null)
            return null;

        final NoteCommand notesCommand = new NoteCommand();
        notesCommand.setId(source.getId());
        notesCommand.setRecipeNotes(source.getRecipeNotes());
        return notesCommand;
    }
}
