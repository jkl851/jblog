select * from blog;
select * from category;
select * from post;
select * from user;
delete from user where id='jkl851';

insert into category values (null, '파이썬', '파이썬 코딩 블라블라', 'jkl851');
insert into category values (null, '자바스크립트', '자바스크립트 코딩 블라블라', 'jkl851');

insert into post values (null, '파이썬 입문', '아아아아아아아아아아ㅏ가가가가가', now(), 1);

insert into user values ('jkl851', '김민철',  '123', now());
select id, name from user where id='jkl851' and password='123';

delete from category where no=3;
delete from post where no=4;

update blog set title='PEPE', logo='123dsfsdfsd' where id='jkl851';

update blog set logo='/images/default.jpg' where id='jkl851';

select c.no, c.name, c.desc, c.blog_id, ifnull((b.cnt) , '0') as count
   from category c left join (select category_no ,count(*) as cnt
								from post
								group by category_no) b 
	on c.no = b.category_no
	where c.blog_id = 'jkl851';
    
select name from category where blog_id='jkl851';

insert into post(no, title, contents, reg_date, category_no) select null, '옵티머스프라임', 'dsadsadsadasdas', now(), no from category where name='트랜스포머' and blog_id='jkl851';

select id, title, logo from blog where id='jkl851';

select no, title, contents, reg_date from post where category_no=7 and no=3;

select c.no as no, c.name as name, ifnull((b.cnt) , '0') as postCounts
			   from category c left join (select category_no ,count(*) as cnt
											from post
											group by category_no) b 
				on c.no = b.category_no
				where c.blog_id = 'jkl851'
			    order by no desc;

select b.no as postNo, b.title as title, b.contents as contents, 
		b.reg_date as reg_date, b.category_no as catNo 
		from post b join (select * from category order by no desc limit 1) c 
		on c.no = b.category_no where c.blog_id = 'jkl851' order by reg_date desc;
        
select b.no as postNo, b.title as title, b.contents as contents, 
		b.reg_date as reg_date, b.category_no as catNo 
		from post b join (select * from category where no=7) c 
		on c.no = b.category_no where c.blog_id = 'jkl851' order by reg_date desc;
        


