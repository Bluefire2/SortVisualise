This little piece of software graphically visualises sorting algorithms.

## Basic use
To use, head over to [releases](https://github.com/Bluefire2/SortVisualise/releases), download the `.jar` file, and run it. Then, select the sorting algorithm, the array size (limited) and the timing delay (`1ms` recommended), and click start to see the algorithm in action!

### Helping out
If you want, you can write another sort implementation to be visualised. To do this, you must write your class to extend `org.bluefire2.SortVisualise.Sorts.Sort` and be stored in the same directory. Check out the already implemented sorts to see how it's done. Basically, write the sort as you normally would, but for swaps, array lookups and comparisons of array elements, you must use one of the helper methods provided in the `Sort` class.

Finally, to make it appear in the GUI, add the line `sortImplementations.put("Your sort", "ClassName");` to `App.java`, after the similar-looking lines for the other sorts.