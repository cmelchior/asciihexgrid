Ascii Hexagonal Grid Pretty Printer
=======================

Ascii Hexagonal Grid Pretty Printer is a Java based tool that can output a hexagonal grid to the commandline.
This enables a visual inspection of the state of a hex grid which can be usefull for eg. debugging hexagonal grid states.

The tool supports flat and pointed hexes, 2 lines of text, a custom filler char as well as different hex sizes.


1. Usage
-----------------------
Include the file 'deploy/asciihexgrid-1.0.jar' in your classpath.

Build a hex grid like this:

    AsciiBoard board = new AsciiBoard(0, 2, 0, 1, new SmallFlatAsciiHexPrinter());
    board.printHex("HX1","-B-", '#', 0, 0);
    board.printHex("HX2","-W-", '+', 1, 0);
    board.printHex("HX3","-W-", '-', 2, 0);
    board.printHex("HX3","-B-", '•', 2, 1);
    board.prettyPrint(true);

output will look like this:

    | = = = = = = = = = = = = |
    |    _ _                  |
    |  /# # #\                |
    | /# HX1 #\ _ _           |
    | \# -A- #/+ + +\         |
    |  \#_#_#/+ HX2 +\ _ _    |
    |        \+ -B- +/- - -\  |
    |         \+_+_+/- HX3 -\ |
    |               \- -C- -/ |
    |                \-_-_-/  |
    |                /• • •\  |
    |               /• HX4 •\ |
    |               \• -D- •/ |
    |                \•_•_•/  |
    |                         |
    | = = = = = = = = = = = = |

2. Coordinate system
-----------------------

The hex grids uses a trapezoidal/axial coordinate system. The axis' look a little different depending on flat or
pointed orientation.

Flat orientation:

              _ _
            /     \
       _ _ /(0,-1) \ _ _
     /     \  -R   /     \
    /(-1,0) \ _ _ /(1,-1) \
    \  -Q   /     \       /
     \ _ _ / (0,0) \ _ _ /
     /     \       /     \
    /(-1,1) \ _ _ / (1,0) \
    \       /     \  +Q   /
     \ _ _ / (0,1) \ _ _ /
           \  +R   /
            \ _ _ /

Pointy orientation:

           / \     / \
         /     \ /     \
        | -1,-1 |  1,-1 |
        |   -R  |       |
       / \     / \     / \
     /     \ /     \ /     \
    | -1,0  |  0,0  |  1,0  |
    |  -Q   |       |   +Q  |
     \     / \     / \     /
       \ /     \ /     \ /
        | -1,1  |  0,1  |
        |       |   +R  |
         \     / \     /
           \ /     \ /

Negative coordinates are currently not supported, ie. (0,0) is the top-left corner.
If you hex grid have negative coordinates, they can be shifted correctly by the following algorithm (pseducode):

    int adjustQ = hexes.getMinQ();
    int adjustR = hexes.getMinR();
    board.printHex("HX1","-A-", hex.getQ() - adjustQ, hex.getR() - adjust);


3. Examples
-----------------------
Currently 4 types of hexes are supported: Small/large flat and small/large pointed. Examples can be seen below:

Small flat:

       _ _
     /• • •\
    /• HX1 •\ _ _
    \• -B- •/- - -\
     \•_•_•/- HX2 -\ _ _
           \- -W- -/- - -\
            \-_-_-/- HX3 -\
                  \- -W- -/
                   \-_-_-/
                   /• • •\
                  /• HX4 •\
                  \• -B- •/
                   \•_•_•/

Large flat:

       _ _ _ _
      / • • • \
     /• • • • •\
    /•   HX1   •\_ _ _ _
    \•   -B-   •/ - - - \
     \• • • • •/- - - - -\
      \_•_•_•_/-   HX2   -\_ _ _ _
              \-   -W-   -/ - - - \
               \- - - - -/- - - - -\
                \_-_-_-_/-   HX3   -\
                        \-   -W-   -/
                         \- - - - -/
                          \_-_-_-_/
                          / • • • \
                         /• • • • •\
                        /•   HX4   •\
                        \•   -B-   •/
                         \• • • • •/
                          \_•_•_•_/

Small pointy:

       /•\     /-\     /-\
     /• • •\ /- - -\ /- - -\
    |• HX1 •|- HX2 -|- HX3 -|
    |• -B- •|- -W- -|- -W- -|
     \• • •/ \- - -/ \- - -/•\
       \•/     \-/     \-/• • •\
                        |• HX4 •|
                        |• -B- •|
                         \• • •/
                           \•/

Large pointy:

         /•\         /-\         /-\
       /• • •\     /- - -\     /- - -\
     /• • • • •\ /- - - - -\ /- - - - -\
    |•   HX1   •|-   HX2   -|-   HX3   -|
    |•   -B-   •|-   -W-   -|-   -W-   -|
    |• • • • • •|- - - - - -|- - - - - -|
     \• • • • •/ \- - - - -/ \- - - - -/•\
       \• • •/     \- - -/     \- - -/• • •\
         \•/         \-/         \-/• • • • •\
                                  |•   HX4   •|
                                  |•   -B-   •|
                                  |• • • • • •|
                                   \• • • • •/
                                     \• • •/
                                       \•/



4. Build
-----------------------
For building the jar file. Gradle 1.6 is needed. Run

    > gradle uploadArchives

from the command line. The jar can now be found in deploy/asciihexgrid-1.0.jar.


5. Credit
-----------------------
For an incredible useful and throrough guide on all things hexagonal grid related, see this excellent blog post by
Red Blob Games:

http://www.redblobgames.com/grids/hexagons/






