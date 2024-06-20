(function() {
    var canvas = document.createElement('canvas'),
        ctx = canvas.getContext('2d'),
        w = canvas.width = innerWidth,
        h = canvas.height = innerHeight,
        particles = [],
        properties = {
            bgColor: 'rgba(17, 17, 19, 1)',
            particleColor: 'rgba(255, 255, 255, 1)',
            particleRadius: 6,
            particleCount: 40,
            particleMaxVelocity: 0.5,
            particleLife: 6,
        };

    document.querySelector('body').appendChild(canvas);

    window.onresize = function() {
        w = canvas.width = innerWidth,
        h = canvas.height = innerHeight;
    }

    class Particle {
        constructor() {
            this.x = Math.random() * w;
            this.y = Math.random() * h;
            this.velocityX = Math.random() * (properties.particleMaxVelocity * 2) - properties.particleMaxVelocity;
            this.velocityY = Math.random() * (properties.particleMaxVelocity * 2) - properties.particleMaxVelocity;
            this.life = Math.random() * properties.particleLife * 60;
        }
        position() {
            if ((this.x + this.velocityX > w && this.velocityX > 0) || (this.x + this.velocityX < 0 && this.velocityX < 0)) {
                this.velocityX *= -1;
            }
            if ((this.y + this.velocityY > h && this.velocityY > 0) || (this.y + this.velocityY < 0 && this.velocityY < 0)) {
                this.velocityY *= -1;
            }
            this.x += this.velocityX;
            this.y += this.velocityY;
        }
        reDraw() {
            ctx.beginPath();
            this.drawStar(ctx, this.x, this.y, 4, properties.particleRadius, properties.particleRadius / 2);
            ctx.closePath();
            ctx.fillStyle = properties.particleColor;
            ctx.fill();
        }
        reCalculateLife() {
            if (this.life < 1) {
                this.x = Math.random() * w;
                this.y = Math.random() * h;
                this.velocityX = Math.random() * (properties.particleMaxVelocity * 2) - properties.particleMaxVelocity;
                this.velocityY = Math.random() * (properties.particleMaxVelocity * 2) - properties.particleMaxVelocity;
                this.life = Math.random() * properties.particleLife * 50;
            }
            this.life--;
        }
        drawStar(ctx, x, y, spikes, outerRadius, innerRadius) {
            var rot = Math.PI / 2 * 3;
            var cx = x;
            var cy = y;
            var step = Math.PI / spikes;

            ctx.moveTo(cx, cy - outerRadius);
            for (let i = 0; i < spikes; i++) {
                x = cx + Math.cos(rot) * outerRadius;
                y = cy + Math.sin(rot) * outerRadius;
                ctx.lineTo(x, y);
                rot += step;

                x = cx + Math.cos(rot) * innerRadius;
                y = cy + Math.sin(rot) * innerRadius;
                ctx.lineTo(x, y);
                rot += step;
            }
            ctx.lineTo(cx, cy - outerRadius);
        }
    }

    function reDrawBackground() {
        ctx.fillStyle = properties.bgColor;
        ctx.fillRect(0, 0, w, h);
    }

    function reDrawParticles() {
        for (var i in particles) {
            particles[i].reCalculateLife();
            particles[i].position();
            particles[i].reDraw();
        }
    }

    function loop() {
        reDrawBackground();
        reDrawParticles();
        requestAnimationFrame(loop);
    }

    function init() {
        for (var i = 0; i < properties.particleCount; i++) {
            particles.push(new Particle);
        }
        loop();
    }

    init();
}())