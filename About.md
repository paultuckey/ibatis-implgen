# Goal: Simplify the use SQL in Java #

## Requirements: ##

  * Ability to easily cut and paste SQL into "proper" sql development tool (Toad, Embarcadero etc).
  * Don't repeat marshaling instructions


## FAQ ##

### I want my DBA/SQL team to write sql for java app why would I want to put SQL in java source? ###

Use stored procedures or functions. This creates a very clear definition between teams.
The Java team can maintain the calls to the procs and the SQL team the procs themselves.