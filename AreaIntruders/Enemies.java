import javax.swing.*;

public class Enemies extends Character {

        private Bomb bomb;

        public Enemies(int x, int y) {

            initEnemies(x, y);
        }

        private void initEnemies(int x, int y) {

            this.x = x;
            this.y = y;

            bomb = new Bomb(x, y);

            var alienImg = "alien.png";
            var ii = new ImageIcon(alienImg);

            setImage(ii.getImage());
        }

        public void act(int direction) {

            this.x += direction;
        }

        public Bomb getBomb() {

            return bomb;
        }

        public class Bomb extends Character{

            private boolean destroyed;

            public Bomb(int x, int y) {

                initBomb(x, y);
            }

            private void initBomb(int x, int y) {

                setDestroyed(true);

                this.x = x;
                this.y = y;

            }

            public void setDestroyed(boolean destroyed) {

                this.destroyed = destroyed;
            }

            public boolean isDestroyed() {

                return destroyed;
            }
        }
    }
