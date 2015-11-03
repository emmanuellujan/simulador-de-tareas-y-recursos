Para que el proyecto pueda correr se deben ajustar las siguiente opciones de configuración:

**En Configurator.java:**

Suplantar la línea:

> String confFile = "conf.xml";

por el path completo del archivo. Por ejemplo en GNU/Linux:

> String confFile = "/media/7a9cedf1-b094-440e-b619-c03d0ebfa4e2/projects/prj/unicen/diseño/tasks-on-resources-simulator/src/conf.xml";

**Luego cambiar el path de ioDirectory dentro del archivo conf.xml. Por ejemplo en GNU/Linux:**



&lt;ioDirectory&gt;

/media/7a9cedf1-b094-440e-b619-c03d0ebfa4e2/projects/prj/unicen/diseño/tasks-on-resources-simulator/test\_cases/

&lt;/ioDirectory&gt;

