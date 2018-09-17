package technophile.app.com.todomvp.ui.add_task;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import technophile.app.com.todomvp.R;
import technophile.app.com.todomvp.repository.local.Task;
import technophile.app.com.todomvp.repository.local.TaskDao;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AddTaskPresenterTest {

    AddTaskPresenter presenter;

    @Mock
    AddTaskView view;

    @Mock
    TaskDao taskDao;

    @Before
    public void setUp() throws Exception {
        presenter = new AddTaskPresenter(view, taskDao);
    }

    @Test
    public void shouldShowErrorIfTaskIsEmpty() {
        // step 1: Provide your input.
        Mockito.when(view.getTaskTitle()).thenReturn("");

        presenter.initiateAddTask();

        Mockito.verify(view).showMessage(R.string.err_empty_task);

    }

    @Test
    public void shouldShowErrorIfTaskDescIsEmpty() {
        // step 1: Provide your input.
        Mockito.when(view.getTaskTitle()).thenReturn("test");
        Mockito.when(view.getTaskDescription()).thenReturn("");

        presenter.initiateAddTask();

        Mockito.verify(view).showMessage(R.string.err_empty_description);

    }

    @Test
    public void shouldShowSuccessMessageIfTaskAdded() {
        Mockito.when(view.getTaskTitle()).thenReturn("DummyTitle");
        Mockito.when(view.getTaskDescription()).thenReturn("DummyDescription");
        Mockito.when(taskDao.insertTask(Mockito.any())).thenReturn(1L);

        presenter.initiateAddTask();

        Mockito.verify(view).showMessage(R.string.msg_task_added);
        Mockito.verify(view).performSuccessOperation();
    }

    @After
    public void tearDown() throws Exception {
    }
}