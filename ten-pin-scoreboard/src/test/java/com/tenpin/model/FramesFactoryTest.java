package com.tenpin.model;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class FramesFactoryTest {

    @InjectMocks
    private FramesFactory factory;
    List<Roll> rollList;

    @Test
    public void testShouldDifferentiateBetweenThrows(){
        rollList = new ArrayList<>();
        rollList.add(new Roll("a", "0"));
        rollList.add(new Roll("a", "5"));
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "F"));
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "4"));
        Frame frame1 = new Regular(new Roll("a", "0"), new Roll("a", "5"), null);
        Frame frame2 = new Strike(new Roll("a", "10"), null);
        Frame frame3 = new Spare(new Roll("a", "F"), new Roll("a", "10"), null);
        Frame frame4 = new Incomplete(new Roll("a", "4"));
        frame1.setNext(frame2);
        frame2.setNext(frame3);
        frame3.setNext(frame4);
        List<Frame> frames = new ArrayList<>(Arrays.asList(frame1,frame2,frame3,frame4));

        Assert.assertEquals(frames.get(0).mark(),factory.build(rollList).get(0).mark());
        Assert.assertEquals(frames.get(0).score(),factory.build(rollList).get(0).score());
    }

    @Test
    public void testShouldCreateLastFrameForXXX(){
        List<Frame> frames = createTillTerminal();
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "10"));
        Frame frame1 = new Strike(new Roll("a", "10"), null);
        Frame frame2 = new Strike(new Roll("a", "10"), null);
        Frame frame3 = new Strike(new Roll("a", "10"), null);
        Frame terminal = Terminal.builder().one(frame1).two(frame2).three(frame3).build();
        frames.get(8).setNext(terminal);
        frame1.setNext(frame2);
        frame2.setNext(frame3);

        Assert.assertEquals(frames.get(0).score(),factory.build(rollList).get(0).score());
        Assert.assertEquals(frames.get(8).mark(),factory.build(rollList).get(8).mark());
    }

    @Test
    public void testShouldCreateLastFrameForXS(){
        List<Frame> frames = createTillTerminal();
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "1"));
        rollList.add(new Roll("a", "9"));
        Frame frame1 = new Strike(new Roll("a", "10"), null);
        Frame frame2 = new Spare(new Roll("a", "1"),new Roll("a", "9"), null);
        Frame terminal = Terminal.builder().one(frame1).two(frame2).build();
        frames.get(8).setNext(terminal);
        frame1.setNext(frame2);

        Assert.assertEquals(frames.get(0).score(),factory.build(rollList).get(0).score());
        Assert.assertEquals(frames.get(8).mark(),factory.build(rollList).get(8).mark());
    }

    @Test
    public void testShouldCreateLastFrameForXXI(){
        List<Frame> frames = createTillTerminal();
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "9"));
        Frame frame1 = new Strike(new Roll("a", "10"), null);
        Frame frame2 = new Strike(new Roll("a", "10"), null);
        Frame frame3 = new Incomplete(new Roll("a", "9"));
        Frame terminal = Terminal.builder().one(frame1).two(frame2).three(frame3).build();
        frames.get(8).setNext(terminal);
        frame1.setNext(frame2);
        frame2.setNext(frame3);

        Assert.assertEquals(frames.get(0).score(),factory.build(rollList).get(0).score());
        Assert.assertEquals(frames.get(8).mark(),factory.build(rollList).get(8).mark());
    }

    @Test
    public void testShouldCreateLastFrameForXR(){
        List<Frame> frames = createTillTerminal();
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "1"));
        rollList.add(new Roll("a", "8"));
        Frame frame1 = new Strike(new Roll("a", "10"), null);
        Frame frame2 = new Regular(new Roll("a", "1"),new Roll("a", "8"), null);
        Frame terminal = Terminal.builder().one(frame1).two(frame2).build();
        frames.get(8).setNext(terminal);
        frame1.setNext(frame2);

        Assert.assertEquals(frames.get(0).score(),factory.build(rollList).get(0).score());
        Assert.assertEquals(frames.get(8).mark(),factory.build(rollList).get(8).mark());
    }

    @Test
    public void testShouldCreateLastFrameForSI(){
        List<Frame> frames = createTillTerminal();
        rollList.add(new Roll("a", "1"));
        rollList.add(new Roll("a", "9"));
        rollList.add(new Roll("a", "2"));
        Frame frame1 = new Spare(new Roll("a", "1"),new Roll("a", "9"), null);
        Frame frame2 = new Incomplete(new Roll("a", "2"));
        Frame terminal = Terminal.builder().one(frame1).two(frame2).build();
        frames.get(8).setNext(terminal);
        frame1.setNext(frame2);

        Assert.assertEquals(frames.get(0).score(),factory.build(rollList).get(0).score());
        Assert.assertEquals(frames.get(8).mark(),factory.build(rollList).get(8).mark());
    }

    @Test
    public void testShouldCreateLastFrameForXI(){
        List<Frame> frames = createTillTerminal();
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "9"));
        Frame frame1 = new Strike(new Roll("a", "10"), null);
        Frame frame2 = new Incomplete(new Roll("a", "9"));
        Frame terminal = Terminal.builder().one(frame1).two(frame2).build();
        frames.get(8).setNext(terminal);
        frame1.setNext(frame2);

        Assert.assertEquals(frames.get(0).score(),factory.build(rollList).get(0).score());
        Assert.assertEquals(frames.get(8).mark(),factory.build(rollList).get(8).mark());
    }

    @Test
    public void testShouldCreateLastFrameForX(){
        List<Frame> frames = createTillTerminal();
        rollList.add(new Roll("a", "10"));
        Frame frame1 = new Strike(new Roll("a", "10"), null);
        Frame terminal = Terminal.builder().one(frame1).build();
        frames.get(8).setNext(terminal);

        Assert.assertEquals(frames.get(0).score(),factory.build(rollList).get(0).score());
        Assert.assertEquals(frames.get(8).mark(),factory.build(rollList).get(8).mark());
    }

    @Test
    public void testShouldCreateLastFrameForS(){
        List<Frame> frames = createTillTerminal();
        rollList.add(new Roll("a", "9"));
        rollList.add(new Roll("a", "1"));
        Frame frame1 = new Spare(new Roll("a", "9"),new Roll("a", "1"), null);
        Frame terminal = Terminal.builder().one(frame1).build();
        frames.get(8).setNext(terminal);

        Assert.assertEquals(frames.get(0).score(),factory.build(rollList).get(0).score());
        Assert.assertEquals(frames.get(8).mark(),factory.build(rollList).get(8).mark());
    }

    @Test
    public void testShouldCreateLastFrameForR(){
        List<Frame> frames = createTillTerminal();
        rollList.add(new Roll("a", "2"));
        rollList.add(new Roll("a", "1"));
        Frame frame1 = new Regular(new Roll("a", "2"),new Roll("a", "1"), null);
        Frame terminal = Terminal.builder().one(frame1).build();
        frames.get(8).setNext(terminal);

        Assert.assertEquals(frames.get(0).score(),factory.build(rollList).get(0).score());
        Assert.assertEquals(frames.get(8).mark(),factory.build(rollList).get(8).mark());
    }

    private List<Frame> createTillTerminal() {
        rollList = new ArrayList<>();
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "10"));
        rollList.add(new Roll("a", "10"));
        Frame frame1 = new Strike(new Roll("a", "10"), null);
        Frame frame2 = new Strike(new Roll("a", "10"), null);
        Frame frame3 = new Strike(new Roll("a", "10"), null);
        Frame frame4 = new Strike(new Roll("a", "10"), null);
        Frame frame5 = new Strike(new Roll("a", "10"), null);
        Frame frame6 = new Strike(new Roll("a", "10"), null);
        Frame frame7 = new Strike(new Roll("a", "10"), null);
        Frame frame8 = new Strike(new Roll("a", "10"), null);
        Frame frame9 = new Strike(new Roll("a", "10"), null);
        frame1.setNext(frame2);
        frame2.setNext(frame3);
        frame3.setNext(frame4);
        frame4.setNext(frame5);
        frame5.setNext(frame6);
        frame6.setNext(frame7);
        frame7.setNext(frame8);
        frame8.setNext(frame9);
        frame9.setNext(null);
        return new ArrayList<>(Arrays.asList(frame1,frame2,frame3,frame4,frame5,frame6,frame7,frame8,frame9));
    }

}