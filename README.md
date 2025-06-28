# 🎬 movietix-engine

A basic JDBC-based movie ticket booking system I built… mostly for practice (and a little bit of fun).

---

## 🛠️ Why this project?

It’s been a while since I worked with JDBC. Just wanted to get my hands dirty again — no Spring, no Hibernate, no shortcuts.  
Sometimes, going back to raw SQL and manual table creation from Java helps you think clearer.

Honestly? Just a timepass project.  
Wanted to practice the flow, refresh DB connections, foreign keys, and understand how each layer interacts.

---

## 🚀 What it does

- Lets users register and log in
- View movie listings
- Book tickets for available shows
- Admins can add movies, theaters, shows
- Handles foreign key constraints properly (no manual DB edits needed)
- All tables and sample data created directly from Java — no MySQL Workbench or SQL scripts

---

## 🧱 Tech Stack

- Java (JDBC)
- MySQL
- No frameworks. Just the bare bones.

---

## 🧠 What I practiced

- Writing clean DAO layers
- Handling foreign keys and DB setup through Java
- Login + registration + session-like flow
- Multi-table queries (joins for booking history, movie-show-theater linkages)
- A bit of structure and sanity in plain Java apps

---

## 🔧 How to run

1. Make sure MySQL is running locally.
2. Update your MySQL password in `DBConnection.java`.
3. Run the `Main` class from `controller` package.
4. Tables will auto-create on first run.
5. Follow prompts in console.

---

## ⚠️ Disclaimer

Not meant for production.  
No password hashing, no GUI, no framework, no security layers — just core logic.

---

## 🎉 Just here to learn

Built this to revisit JDBC after a long break.  
If you’re doing something similar, feel free to fork or reuse anything you like.

Let’s face it — not every project needs to be a startup idea.  
Sometimes, it's okay to just build and reset your brain. 🧠🔁

---

`#JDBC` `#practiceproject` `#consoleapp` `#devbasics` `#movietix-engine`
