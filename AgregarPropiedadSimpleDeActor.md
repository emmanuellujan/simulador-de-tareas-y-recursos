Supongamos que se quiere agregar la cantidad de tareas máxima: maxTasks

**En el xml de entrada: test\_case\_8.xml se puede encontrar:
...**

&lt;actor&gt;


> 

&lt;actorId&gt;

actor1

&lt;/actorId&gt;


> 

&lt;capacity&gt;

10

&lt;/capacity&gt;


> 

&lt;schedulingAlgorithm&gt;

FCFS

&lt;/schedulingAlgorithm&gt;


> 

&lt;quantum&gt;

-1

&lt;/quantum&gt;




&lt;/actor&gt;


...

Simplemente se agrega la nueva etiqueta a cada actor:

...


&lt;actor&gt;


> 

&lt;actorId&gt;

actor1

&lt;/actorId&gt;


> 

&lt;capacity&gt;

10

&lt;/capacity&gt;


> 

&lt;maxTasks&gt;

10

&lt;/maxTasks&gt;


> 

&lt;schedulingAlgorithm&gt;

FCFS

&lt;/schedulingAlgorithm&gt;


> 

&lt;quantum&gt;

-1

&lt;/quantum&gt;




&lt;/actor&gt;


...

**Luego en la clase XMLInputSystem, en la función loadActorsList se puede imitar lo que está hecho para el actorId:**

//actorId<br>
NodeList actorIdElementList = element.getElementsByTagName("actorId");<br>
Element actorIdElement = (Element) actorIdElementList.item(0);<br>
NodeList actorId = actorIdElement.getChildNodes();<br>
String sActorId = ((Node) actorId.item(0)).getNodeValue();<br>
...<br>
Actor actor = new Actor(sActorId, sAlgorithm, iQuantum, schedulingSystem, Integer.parseInt(sActorCapacity));<br>
<br>
Y se puede agregar:<br>
<br>
//maxTasks<br>
NodeList maxTasksElementList = element.getElementsByTagName("maxTasks");<br>
Element maxTasksElement = (Element) maxTasksElementList.item(0);<br>
NodeList maxTasks = maxTasksElement.getChildNodes();<br>
String sMaxTasks = ((Node) maxTasks.item(0)).getNodeValue();<br>
...<br>
Actor actor = new Actor(sActorId, sAlgorithm, iQuantum, schedulingSystem, Integer.parseInt(sActorCapacity), Integer.parseInt(sMaxTasks));<br>
<br>
<br>
