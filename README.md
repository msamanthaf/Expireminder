# EXPIREMINDER

## Expiration date notifier

**Overview**:
- A program that allows users to track and add items to their own custom categories
  and receive an email notification once they reached the set amount of time before its expiry date.

- This program is intended to be accessible to all so that it can be used by anyone who wants to keep track of their 
items' expiration dates and receive notifications before they expire. This may include individuals who want to ensure 
their personal items such as foods, supplements and medicine, cosmetics, coupons, membership, or important documents, 
as well as businesses that want to keep track of their inventory expiration dates.

- This project was inspired by my recent personal experience of finding expired oatmeal in my dorm room.
The frustration is real, and I can relate to the need for an application like this. 
I plan to use this app myself to keep track of my own items and ensure that I am aware of them before they expire.
I also believe that an app that can help keep track of expiration dates and notify users before they expire could be 
very useful for anyone who wants to avoid losing their item's worth especially their important documents.

**User Stories**:
- As a user, I would like to add my name and email address at the start to get notified.
- I would also like to have an account page so that I can modify my name and email address.
- At the main page, I would like to be able to add new categories of items and their group color (if possible).
- I would also like to be able to rename, delete, and change the color of each category.
- I want to be able to add, remove, and edit an item, their quantity, and their expiration date.
- I want to be able to add the same item with the same name because some quantities might have a different expiry date.
- Make sure that I am not going to receive any notifications to a new input item with a date that is in the past.
- I would like to have a small status icon next to the item represented as: Green means good, 
Yellow means expires soon (1 month), Red means expired.

- As a user, I want to have an option to save my progress when exiting the program.
- As a user, I want to have an option to load my progress from a file.

## Instructions for Grader

- You can generate the first required action related to adding Xs to a Y by pressing the "Add Category" button at the homepage after making an account
- You can generate the second required action related to adding Xs to a Y by pressing the "Add Item" button at the homepage after making an account
- You can locate my visual component by running the main application
- You can save the state of my application by clicking the "Yes, save my progress" button at the homepage after making an account
- You can reload the state of my application by clicking the "Yes, load data" button

## "Phase 4: Task 2"
- Sample event log printed to console:
  Account created.
  Account modified.
  Category: Cat 1 added.
  Item: Expired added.
  Category: Cat 2 added.
  Item: Good condition added.
  Item: Expired modified.
  Notification sent for item: Expiring soon.
  Item: Good condition deleted.
  Category: Cat 2 and its items are deleted.
