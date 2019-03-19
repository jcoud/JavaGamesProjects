import javax.swing.*;
import java.awt.*;
import java.net.URI;
import java.util.LinkedList;
import java.util.Random;

class reference {
    // === about
    static int tick;
    static Timer timer;
    static final String
            version = "#version",
            author = "Andrew Swan (a.k.a jcoud)",
            link = "httpsgithub.com/jcoud";
    // ===
    static Point
            canvas_startingPoint,
            head, fruit;
    static final int bs = 25;
    static int
            grid_width, grid_height, level,
            snakeLength, score, delay = 20 ;
    static LinkedList<Point> snake = new LinkedList<>();
    static String
            /*DIRECTION*/
            DIR_CURRENT,
            DIR_UP = "dir_up",
            DIR_DOWN = "dir_down",
            DIR_LEFT = "dir_left",
            DIR_RIGHT = "dir_right";
    static boolean
            /*STATUS*/      /*STATES*/
            st_running,     showGameText,
            st_gameOver,    colorType_random = false,
            st_newGame,     colorType_default = true,
            st_pause,       showControlInfo,

            toggle_keys,
            showGrid = true;
    static JMenuBar gameMenuBar;
    static Color
            rn_color_body,
            rn_color_head,
            rn_color_fruit;
    static final int gap = 14;
    static JPanel message;
    static URI uri;

    static void randomizeSnakeColor() {
        Random random   = new Random();
        rn_color_body   = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        rn_color_head   = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        rn_color_fruit  = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
    }
    /*Getters & Setters*//*
    static class GameParam {
        static boolean getKeySwitch()       {return toggle_keys;}
        static String getVersion()          {return version;}
        static String getAuthor()           {return author;}
        static String getLink()             {return link;}
        static Dimension getDim()           {return dim;}

        static JMenuBar getGameMenuBar()    {return gameMenuBar;}
        static int getDelay()               {return delay;}
        static int getScore()               {return score;}
        static int getLevel()               {return level;}
        static int getBlockSize()           {return bs;}

        static void setBlockSize(int key)   {bs = key;}
        static void setLevel(int key)       {level = key;}
        static void setScore(int key)       {score = key;}
        static void setDelay(int key)       {delay = key;}
        static void setKeySwitch(boolean key)       {toggle_keys = key;}
        static void setGameMenuBar(JMenuBar key)    {gameMenuBar = key;}
    }

    static class GameStatus{

        static boolean isRunning()      {return st_running;}
        static boolean isGameOver()     {return st_gameOver;}
        static boolean isNewGame()      {return st_newGame;}
        static boolean isPaused()       {return st_pause;}

        static void setRunning(boolean key)     {st_running = key;}
        static void setGameOver(boolean key)    {st_gameOver = key;}
        static void setPause(boolean key)       {st_pause = key;}
        static void setNewGame(boolean key)     {st_newGame = key;}
    }
    static class SnakeDirection{
        static String getCurrentDir()   {return DIR_CURRENT;}
        static boolean isUp()           {return DIR_UP;}
        static boolean isDown()         {return DIR_DOWN;}
        static boolean isLeft()         {return DIR_LEFT;}
        static boolean isRight()        {return DIR_RIGHT;}

        static void setCurrentDir(String key)   {DIR_CURRENT = key;}
        static void setUp(boolean key)          {DIR_UP = key;}
        static void setDown(boolean key)        {DIR_DOWN = key;}
        static void setLeft(boolean key)        {DIR_LEFT = key;}
        static void setRight(boolean key)       {DIR_RIGHT = key;}
    }
    static class SnakeProp{
        static LinkedList<Point> getSnakeBody() {return snake;}
        static Point getHead()      {return head;}
        static Point getFruit()     {return fruit;}
        static int getSnakeLen()    {return snakeLength;}

        static void setSnakeLen(int key) {snakeLength = key;}
        static void setHead(Point key)  {head = key;}
        static void setFruit(Point key) {fruit = key;}

    }
    static class GameStates{
        static boolean showingGameInfo()    {return showGameText;}
        static boolean isColorRandom()      {return colorType_random;}
        static boolean isColorDefault()     {return colorType_default;}
        static boolean showingControlInfo() {return showControlInfo;}
        static boolean showingGrid()        {return showGrid;}

        //static void setShowGrid(boolean key)    {showGrid = key;}
        static void setShowControlInfo(boolean key) {showControlInfo = key;}
        static void setColorDefault(boolean key)    {colorType_default = key;}
        static void setColorRandom(boolean key)     {colorType_random = key;}
        static void toggleShowGameInfo()            {showGameText = !showGameText;}
        static void toggleShowGrid()                {showGrid = !showGrid;}
    }
    static class CanvasParam{

        //static Point getCanvasOrigin()          {return canvas_origin;}
        static Point getCanvasStartingPoint()   {return canvas_startingPoint;}
        static int getGridWidth()               {return grid_width;}
        static int getGridHeight()              {return grid_height;}

        static void setGridWidth(int key)       {grid_width = key;}
        static void setGridHeight(int key)      {grid_height = key;}
        static void setCanvasStartingPoint(Point key) {canvas_startingPoint = key;}
    }
    static class SnakeColor{
        static Color rn_color_body;
        static Color rn_color_head;
        static Color rn_color_fruit;
        static void randomizeSnakeColor() {
            Random random   = new Random();
            rn_color_body   = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            rn_color_head   = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
            rn_color_fruit  = new Color(random.nextInt(255), random.nextInt(255), random.nextInt(255));
        }

        //static void set

        static Color getRandomColor_Body()  {return rn_color_body;}
        static Color getRandomColor_Head()  {return rn_color_head;}
        static Color getRandomColor_Fruit() {return rn_color_fruit;}
    }*/
}
