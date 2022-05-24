package com.company;

public class SpaceManager
{
        public int start,end,size;
        boolean state;
        public SpaceManager(int start, int end, boolean state) {
            super();
            this.start = start;
            this.end = end;
            this.state = state;
            size=(end-start);
        }

}




