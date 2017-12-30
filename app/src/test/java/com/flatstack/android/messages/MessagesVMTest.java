package com.flatstack.android.messages;

import android.arch.core.executor.testing.InstantTaskExecutorRule;

import com.flatstack.android.messages.models.Dialog;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TestRule;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MessagesVMTest {

    @Rule public TestRule androidArchRule = new InstantTaskExecutorRule();

    @Test
    public void constructor() throws Exception {
        // Arrange
        MessageInteractor mockMessageInteractor = mock(MessageInteractor.class);

        // Act
        MessagesVM messagesVM = new MessagesVM(mockMessageInteractor);

        // Assert
        assertThat(messagesVM.getMessageInteractor()).isEqualTo(mockMessageInteractor);
    }

    @Test
    public void attach() throws Exception {
        // Arrange
        MessageInteractor mockMessageInteractor = mock(MessageInteractor.class);
        MessagesVM messagesVM = new MessagesVM(mockMessageInteractor);
        List<Dialog> expectedMessages = Arrays.asList(
//                new Dialog()
        );
        doReturn(expectedMessages).when(mockMessageInteractor).getMessages();

        // Act
        messagesVM.attach();

        // Assert
        assertThat(messagesVM.showProgress.getValue()).isTrue();
        verify(mockMessageInteractor).getMessages();
        assertThat(messagesVM.messages.getValue()).isEqualTo(expectedMessages);
    }
}
