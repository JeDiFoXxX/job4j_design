select c.name "Машина", cb.name "Кузов", ce.name "Двигатель", ct.name as "КПП"
from cars c 
left join car_bodies cb on c.body_id = cb.id
left join car_engines ce on c.engine_id = ce.id
left join car_transmissions ct on c.transmission_id = ct.id

select cb.name as "Неиспользуемый кузов"
from car_bodies cb 
left join cars c on c.body_id = cb.id
where c.body_id is null;

select ce.name as "Неиспользуемый двигатель"
from car_engines ce 
left join cars c on c.engine_id = ce.id
where c.engine_id is null;

select ct.name as "Неиспользуемая КПП"
from car_transmissions ct 
left join cars c on c.transmission_id = ct.id
where c.transmission_id is null;