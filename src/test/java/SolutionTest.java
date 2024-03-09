import org.junit.Assert;
import org.junit.Test;

public class SolutionTest {
    @Test
    public void test1(){
        int[] servers = {3,3,2};
        int[] tasks = {1,2,3,2,1,2};
        int[] expected = {2,2,0,2,1,2};
        int[] actual = new Solution().assignTasks(servers, tasks);
        Assert.assertArrayEquals(expected, actual);
    }
    @Test
    public void test2(){
        int[] servers = {5,1,4,3,2};
        int[] tasks = {2,1,2,4,5,2,1};
        int[] expected = {1,4,1,4,1,3,2};
        int[] actual = new Solution().assignTasks(servers, tasks);
        Assert.assertArrayEquals(expected, actual);
    }

}
