package com.flatstack.android.messages;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;

import com.flatstack.android.messages.models.Dialog;
import com.flatstack.android.messages.models.Message;
import com.flatstack.android.utils.Contacts;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class MessageInteractorTest {

    @Mock private Context mockContext;
    @Mock private Contacts mockContacts;
    @InjectMocks @Spy private MessageInteractor messageInteractor;

    @Test
    public void constructor() throws Exception {
        // Act
        MessageInteractor messageInteractor = new MessageInteractor(mockContext, mockContacts);

        // Assert
        assertThat(messageInteractor.context).isEqualTo(mockContext);
        assertThat(messageInteractor.contacts).isEqualTo(mockContacts);
    }

    @Test
    public void getMessages() throws Exception {
        // Arrange
        final String expectedMessage = "Fuck you";
        final String expectedName = "Cartman";
        final String expectedDatetime = "Today";
        final String expectedAvatar = ":)";

        List<Message> expectedInbox = Arrays.asList(
                new Message(expectedMessage, expectedName, expectedDatetime)
        );

        List<Dialog> expectedDialogs = Arrays.asList(
                new Dialog(expectedName, expectedAvatar, expectedMessage, expectedDatetime)
        );

        Context mockContext = mock(Context.class);
        MessageInteractor messageInteractor = spy(new MessageInteractor(mockContext, mockContacts));
        doReturn(expectedInbox).when(messageInteractor).retrieveMessages();

        // Act
        List<Dialog> actualDialogs = messageInteractor.getMessages();

        // Assert
        assertThat(actualDialogs).isEqualTo(expectedDialogs);
        verify(messageInteractor).retrieveMessages();
    }

    @Test
    public void getInbox() throws Exception {
        // Arrange
        String expectedMessage = "Hey asshole";
        String expectedName = "Ilya";
        String expectedDatetime = "01.04.2018 4.21";

        List<Message> expectedInboxMessages = Arrays.asList(
                new Message(expectedMessage, expectedName, expectedDatetime)
        );
        ContentResolver mockContentResolver = mock(ContentResolver.class);
        Cursor mockCursor = mock(Cursor.class);
        doReturn(true).when(mockCursor).moveToFirst();
        doReturn(false).when(mockCursor).moveToNext();
        doReturn(expectedMessage).when(mockCursor).getString(0);
        doReturn(expectedName).when(mockCursor).getString(1);
        doReturn(expectedDatetime).when(mockCursor).getString(2);

        doReturn(mockCursor).when(mockContentResolver).query(any(), any(), any(), any(), any());
        doReturn(mockContentResolver).when(mockContext).getContentResolver();

        // Act
        List<Message> actualInboxMessages = messageInteractor.retrieveMessages();

        // Assert
        assertThat(actualInboxMessages).isEqualTo(expectedInboxMessages);
    }

    @Test
    public void getSent() throws Exception {
        // Act

        // Assert
        assertThat("fuck you").isEqualTo("no thanks");
    }
}