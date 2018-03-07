# Google Hashcode 2018 / Team  Minions

## The Task
**Self-driving rides**

Check https://hashcode.withgoogle.com/past_editions.html

## The Result
**Scores**:

| Input            | latest         | best           |
|------------------|----------------|----------------|
| a_example        | 4              | 10             |
| b_should_be_easy | 176,877        | 176,877        |
| c_no_hurry       | 12,456,428     | 13,086,527     |
| d_metropolis     | 11,091,528     | 11,091,006     |
| e_high_bonus     | 21,463,975     | 21,465,945     |
| **Total**        | **45,188,812** | **45,820,365** |
    
**Ranks**: 
- 681 global
- 56 Germany
- 2 Hub

## The Ideas
After creating the basic project structure, a FileReader and a basic algorithm discussion we split into two teams and followed three approaches:

### One
- A weighted look-ahead algorithm on a departure-sorted ride set

### Two
- Random assignment
- Skip random rides

### Three
- Sort the rides by earliest possible pick-up time
- Simply distribute the rides to the cars in a dump loop

The second approach was ready after a few minutes. Performance were as expected: Useless - **~14Mln**

Afterwards we could upload the first "real" contribution of the third approach and fight with a few bugs in the first algorighm.
The Score were somewhere around **38Mln**

## The Optimization
While the first approach seemed to be the most promising we kept stick somewhere and decided to try further.
So For the last two hours we went full into the optimization of the third idea.

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
