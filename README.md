# 🎬 movietix-engine

So I decided to build a movie ticket booking system.

Why?  
Because my brain said, “Let’s relive the glorious JDBC days,” and I didn’t stop it.  
Also because I forgot half of it and needed a reminder. 😅

---

## 🤔 But... why JDBC?

Because sometimes, you just want to suffer.

- No Spring Boot magic.
- No Hibernate to hold your hand.
- Just pure, raw Java and SQL

This was mostly me yelling at foreign keys and figuring out why my `INSERT` failed for the 47th time.  
Turns out… order matters. Who knew? 🤷‍♀️

---

## 🍿 What this thing does

- Users can register (if they behave).
- Admins can add movies, theaters, and shows.
- Shows connect movies ➡ screens ➡ theaters ➡ reality.
- You can book tickets — and yes, it *actually* books them.
- Everything runs in the console. Like the good old days.

No Workbench.  
No GUI.  
Just cold, hard console prompts.

---

## 🔧 Tech Stack (aka things I yelled at):

- Java (with JDBC, of course)
- MySQL (with FOREIGN KEY-induced pain)
- Zero frameworks (my fingers typed every query like a warrior)

---

## 😤 Features I didn’t include

- Password hashing (don’t @ me)
- Remember me buttons
- REST APIs
- Frontend
- Basically everything that looks fancy

This one’s all about the logic and structure — and making sure I didn’t forget what a `ResultSet` is.

---

## 🧠 What I got out of it

- A fresh reminder that databases don’t listen unless you speak their exact dialect.
- Solid practice wiring up DAOs, DTOs, and making everything talk nicely.
- Confidence boost unlocked: “Hey, I can still build something from scratch!”

---

## 🏁 How to run

1. Make sure MySQL is alive and not being dramatic.
2. Update your DB creds in `DBConnection.java`.
3. Run the `Main` class.
4. Tables create themselves (Java handles that, like magic but painful).
5. Start booking imaginary tickets to imaginary movies in your very real terminal.

---

## 💡 Just for fun

This is not some big shiny project for recruiters.  
This is me, trying to shake off the rust — one JDBC call at a time.  

Built mostly out of curiosity, muscle memory, and caffeine.

---

If you’re also stuck in tutorial fatigue and want to remember how things *actually* work — try building something this dumb.

I promise it helps.  
And hey, you might even laugh a bit when `foreign key constraint fails` for no reason. 😂

---

`#jdbc` `#consolelife` `#sqlrage` `#practiceproject` `#devburnouttherapy` `#movietix-engine`
