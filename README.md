<h1>Ten pin bowling scoreboard</h1>
<h2>Tech stack</h2>
<ul>
    <li>Java 8</li>
    <li>Maven 3.6</li>
    <li>Spring boot 2.4</li>
    <li>Jackson 2.6</li>
    <li>Cucumber</li>
    <li>Docker</li>
    <li>Makefiles</li>
</ul>
<h2>How to run</h2>
<p>In order to run, first localize your game.txt file with all the throws for the players</p>
<p>With all this info retrieved, you may begin with the next step, running the application. You may choose to do it 
with docker or without it</p>
<h3>1- Docker</h3>
-- Working with version 19.03.12 - Other will most likely also work but no guarantees --

<p>Run these commands from the root replacing placeholders where necessary</p>
<ul>
    <li>make build (this will take a while)</li>
    <li>make run SCOREBOARD_PATH={Path to game.txt file}</li>
</ul>
<p>For Windows you either have to download Ubuntu's subsystems plugin or adapt and run docker's commands manually from 
the Makefile file adapting syntax where necessary</p>
<p><b>A note on docker executable:</b> Docker automatically converts all tabs separators to spaces. You may need to run
this project natively on your machine in order to see the intended identation</p>
<h3>2- Native</h3>
<p>Run these commands from the root replacing placeholders where necessary</p>
<ul>
    <li>make package</li>
    <li>make package native SCOREBOARD_PATH={Path to game.txt file}</li>
</ul>
<p>For windows follow advice in the docker section.</p>
<h2>How to use</h2>
<p>This is a CLI app without GUI. This project provides an example for games one player might encounter such as:.</p>
perfect game, the worst outcome possible, and an average game with misses, strikes and spares.
<p>Have fun!</p>