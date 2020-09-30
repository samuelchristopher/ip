# User Guide

## Features 

### List Tasks Stored
Display tasks in an ordered list

## Usage

### `list` - List all the tasks

This command will display all the tasks in the form of an ordered list. 

Example of usage: 

`list`

Expected outcome:

```
____________________________________________________________
 1. [T][✓] read book
 2. [D][✗] return book (by: June 6th)
 ____________________________________________________________
```

### Add a todo
Add a todo item to the task list.

## Usage

### `todo` - Add a todo

This command will add a todo to the task list.

Example of usage: 

`todo <description>`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task: 
  [T][✗] one
Now you have 4 tasks in the list.
____________________________________________________________
```

### Add an event
Add an event item to the task list.

## Usage

### `event` - Add a event

This command will add a event to the task list.

Example of usage: 

`event <description> /at <timing>`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task: 
  [E][✗] one (at: 5pm)
Now you have 5 tasks in the list.
____________________________________________________________
```

### Add a deadline
Add a deadline item to the task list.

## Usage

### `deadline` - Add a deadline

This command will add a deadline to the task list.

Example of usage: 

`deadline <description> /by <deadline>`

Expected outcome:

```
____________________________________________________________
Got it. I've added this task: 
  [D][✗] two (by: thursday)
Now you have 6 tasks in the list.
____________________________________________________________
```

### Mark a task as done
Indicate that a task has been completed.

## Usage

### `done` - Mark a task as completed

This command will add mark the task as completed.

Example of usage: 

`done <taskId as seen on the list command>`

Expected outcome:

```
____________________________________________________________
Nice! I've marked this task as done: 
 [D][✓] two (by: thursday)
____________________________________________________________
```

### Delete a task
Remove the task from the task list.

## Usage

### `delete` - Delete a task

This command will delete the task.

Example of usage: 

`delete <taskId as seen on the list command>`

Expected outcome:

```
____________________________________________________________
Noted. I've removed this task: 
 [D][✓] two (by: thursday)
Now you have 4 tasks in the list.
____________________________________________________________
```