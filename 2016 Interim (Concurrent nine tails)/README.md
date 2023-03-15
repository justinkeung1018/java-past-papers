# 2016 Interim (Concurrent nine tails)
There is an error in the `generateParents` function in the `NineTailsWeightedGraph` class. The following line
```
WeightedEdge edge = new WeightedEdge(index, res.newIndex, res.numFlips);
```
should be changed to
```
WeightedEdge edge = new WeightedEdge(res.newIndex, index, res.numFlips);
```
(Otherwise the function will actually be `generateChildren` and the parent-child relationship of the weighted edges will be all wrong.)