SELECT Promocion.id, Promocion.nombre, Promocion.tipo, Promocion.baja, Promocion.descuento, group_concat(promocion_atraccion.atraccion_id) as atracciones
FROM Promocion
LEFT JOIN promocion_atraccion on promocion_atraccion.promocion_id = Promocion.id
WHERE Promocion.tipo = "Porcentual"
GROUP BY Promocion.id;

SELECT Promocion.id, Promocion.nombre, Promocion.tipo, Promocion.costo, group_concat(promocion_atraccion.atraccion_id) as atracciones
FROM Promocion
LEFT JOIN promocion_atraccion on promocion_atraccion.promocion_id = Promocion.id
WHERE Promocion.tipo = "Absoluta"
GROUP BY Promocion.id;

SELECT promo_AxB.id, promo_AxB.nombre, promo_AxB.tipo, promo_AxB.atracciones, group_concat(promoGratuita_axb.atraccion_id) as atracciones_gratis
FROM (
SELECT Promocion.id, Promocion.nombre, Promocion.tipo, group_concat(promocion_atraccion.atraccion_id) as atracciones
FROM Promocion
LEFT JOIN promocion_atraccion on promocion_atraccion.promocion_id = Promocion.id
WHERE Promocion.id = 5
GROUP by Promocion.id
) as "promo_AxB"
INNER JOIN promoGratuita_axb on promoGratuita_axb.promocion_id = promo_AxB.id
GROUP By promo_AxB.id;

SELECT id, atraccion_id
FROM promocion_atraccion
WHERE Promocion_atraccion.promocion_id = 1 AND Promocion_atraccion.atraccion_id = 4

UPDATE Promocion_atraccion SET atraccion_id = 5 WHERE promocion_id = 1 AND atraccion_id = 4