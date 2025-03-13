package psyduck.exception;

/**
 * Error occurs when user does not input a task number in the task list
 * e.g. non-numbers: Three
 * e.g. Task number not in Task list: 5 but there are only 4 Tasks
 */
public class TaskIndexUndefinedException extends Exception {

}
