CREATE OR REPLACE FUNCTION retira_acentos(text) 
RETURNS text AS 
$BODY$ 
select 
translate($1,'באגדהיטךכםלןףעפץצתשûüְֱֲֳִָֹÊֻּֽֿ׃ׂװױײÚÙÛÜחַ', 
'aaaaaeeeeiiiooooouuuuAAAAAEEEEIIIOOOOOUUUUcC'); 
$BODY$ 
LANGUAGE 'sql' IMMUTABLE STRICT; 


