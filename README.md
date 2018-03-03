# Google Hashcode 2018 / Team  Minions

## The Task
**Self-driving rides**

Check https://hashcode.withgoogle.com/past_editions.html

## The Ideas
After creating the basic project structure, a FileReader and after a basic algorithm discussion we split into two teams and followed three approaches:

### One
- A weighted look-ahead algorithm on a departure-sorted ride set

### Two
- Random assignment
- Skip random rides

### Three
- Sort the rides by earliest possible pick-up time
- Simply distribute the rides to the cars in a dump loop

The second approach were ready after a few minutes. Performance were as expected: Useless ~14Mln

Afterwards we could upload the first "real" contributions of the second approach and fought with a few bugs in the first algorighm
The Score were somwhere around 38Mln

## The Optimization
While first approach seemed to be the most promising we stuck somewhere and decided to go ahead.
So For the last two hours we went full into the optimization of the second approach. 

### Skip unreachable rides
- before assigning a ride chech if you will get any points with any ride at all.

### All in for Bonus
- after assigning all rides have a look at the skipped and try to assign them somewhere for a bonus

-> we were unable to implement this one in time (post-competition contribution coming)

Our end score were `45,820,365` as a result of all the intermediate results 
## The Team
- [samy1990](https://github.com/samy1990)
- [DavidEibl](https://github.com/DavidEibl)
- [phischde](https://github.com/phischde)
- [anthu](https://github.com/anthu)

## Acknowledgments
- thanks [Kata Seeds](http://kata-seeds.github.io) for this seed Project
- thanks MaibornWolff Munich for organizing a Hub
