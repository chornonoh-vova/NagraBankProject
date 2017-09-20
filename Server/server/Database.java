p a c k a g e   s e r v e r ;  
  
 i m p o r t   j a v a . s q l . * ;  
  
 p u b l i c   c l a s s   D a t a b a s e   {  
 	 / / f o r m a t   o f   u r l ( j d b c : m y s q l : / / i p : p o r t / d a t a b a s e ) :  
 	 / / " j d b c : m y s q l : / / l o c a l h o s t : 3 3 0 6 / b a n k _ u s e r s " ;  
 	 / / d e f a u l t   u s e r :   " r o o t " ;  
 	 / / d e f a u l t   p a s s w o r d :   " r o o t " ;  
  
 	 p r i v a t e   C o n n e c t i o n   c o n n e c t i o n ;  
 	 p r i v a t e   S t a t e m e n t   s t a t e m e n t ;  
  
 	 / / >=AB@C:B>@ 
 	 p u b l i c   D a t a b a s e ( S t r i n g   u r l ,   S t r i n g   u s e r ,   S t r i n g   p a s s w o r d )   {  
 	 	 t r y   {  
 	 	 	 c o n n e c t i o n   =   D r i v e r M a n a g e r . g e t C o n n e c t i o n ( u r l ,   u s e r ,   p a s s w o r d ) ;  
 	 	 	 s t a t e m e n t   =   c o n n e c t i o n . c r e a t e S t a t e m e n t ( ) ;  
 	 	 }   c a t c h   ( S Q L E x c e p t i o n   e )   {  
 	 	 	 S y s t e m . o u t . p r i n t l n ( e . g e t M e s s a g e ( ) ) ;  
 	 	 }  
 	 }  
  
 	 / / K?>;=5=85  S Q L   70?@>A0 
 	 p r i v a t e   O b j e c t   e x e c u t e ( S t r i n g   t y p e ,   S t r i n g   q u e r y )   t h r o w s   S Q L E x c e p t i o n   {  
 	 	 i f   ( t y p e . e q u a l s ( " i n s e r t " )   | |   t y p e . e q u a l s ( " u p d a t e " ) )   {  
 	 	 	 r e t u r n   s t a t e m e n t . e x e c u t e U p d a t e ( q u e r y ) ;  
 	 	 }   e l s e   i f   ( t y p e . e q u a l s ( " s e l e c t " ) )   {  
 	 	 	 r e t u r n   s t a t e m e n t . e x e c u t e Q u e r y ( q u e r y ) ;  
 	 	 }   e l s e   {  
 	 	 	 r e t u r n   n u l l ;  
 	 	 }  
 	 }  
  
 	 / / !?5F80;L=K9  <5B>4  4;O  >:@C3;5=8O  10;0=A0 
 	 p r i v a t e   v o i d   r o u n d ( )   t h r o w s   S Q L E x c e p t i o n   {  
 	 	 e x e c u t e ( " u p d a t e " ,   " u p d a t e   u s e r s   s e t   b a l a n c e   =   r o u n d ( b a l a n c e ,   2 ) ; " ) ;  
 	 }  
  
 	 / /  538AB@0F8O  :;85=B>2 
 	 p u b l i c   b o o l e a n   r e g i s t r y N e w U s e r ( i n t   p i n ,   S t r i n g   u s e r L o g i n ,   D a t e   b i r t h D a t e ,  
 	 	 S t r i n g   s e c r e t Q u e s t i o n ,   S t r i n g   s e c r e t A n s w e r )   {  
 	 	 t r y   {  
 	 	 	 / / 0E>48<  <0:A8<0;L=>5  7=0G5=85  u s e r I d  
 	 	 	 R e s u l t S e t   m a x i m u m   =   ( R e s u l t S e t )   e x e c u t e ( " s e l e c t " ,  
 	 	 	 " s e l e c t   m a x ( u s e r _ i d )   f r o m   u s e r s ; " ) ;  
 	 	 	 i n t   m a x   =   0 ;  
 	 	 	 w h i l e   ( m a x i m u m . n e x t ( ) )   {  
 	 	 	 	 m a x   =   m a x i m u m . g e t I n t ( 1 ) ;  
 	 	 	 }  
 	 	 	 m a x i m u m . c l o s e ( ) ;  
 	 	 	 e x e c u t e ( " i n s e r t " ,   " i n s e r t   i n t o   u s e r s   v a l u e s ( "  
 	 	 	 	 	 +   + + m a x   +   " , "  
 	 	 	 	 	 +   "   0 , "  
 	 	 	 	 	 +   p i n   +   " , "  
 	 	 	 	 	 +   "   ' "   +   b i r t h D a t e . t o S t r i n g ( 	 )   +   " ' , "  
 	 	 	 	 	 +   "   ' "   +   u s e r L o g i n   +   " ' , "  
 	 	 	 	 	 +   "   ' "   +   s e c r e t Q u e s t i o n   +   " ' , "  
 	 	 	 	 	 +   "   ' "   +   s e c r e t A n s w e r   +   " ' , "  
 	 	 	 	 	 +   "   t r u e ) ; " ) ;  
 	 	 	 r o u n d ( ) ;  
 	 	 	 r e t u r n   t r u e ;  
 	 	 }   c a t c h   ( S Q L E x c e p t i o n   e )   {  
 	 	 	 S y s t e m . o u t . p r i n t l n ( e . g e t M e s s a g e ( ) ) ;  
 	 	 	 r e t u r n   f a l s e ;  
 	 	 }  
 	 }  
  
 	 / / 2B>@870F8O  ?>;L7>20B5;O 
 	 p u b l i c   b o o l e a n   l o g I n ( i n t   p i n ,   S t r i n g   u s e r L o g i n )   {  
 	 	 t r y   {  
 	 	 	 R e s u l t S e t   c l i e n t   =   ( R e s u l t S e t )   e x e c u t e ( " s e l e c t " ,  
 	 	 	 " s e l e c t   p i n ,   u s e r _ l o g i n "  
 	 	 	 +   "   f r o m   u s e r s   w h e r e   p i n   =   "   +   p i n   +   "   a n d "  
 	 	 	 +   "   u s e r _ l o g i n   =   ' "   +   u s e r L o g i n   +   " ' ; " ) ;  
 	 	 	 i f   ( ! c l i e n t . f i r s t ( ) )   {  
 	 	 	 	 r e t u r n   f a l s e ;  
 	 	 	 }   e l s e   {  
 	 	 	 	 r e t u r n   t r u e ;  
 	 	 	 }  
 	 	 }   c a t c h   ( S Q L E x c e p t i o n   e )   {  
 	 	 	 S y s t e m . o u t . p r i n t l n ( e . g e t M e s s a g e ( ) ) ;  
 	 	 	 r e t u r n   f a l s e ;  
 	 	 }  
 	 }  
  
 	 / / !=OB85  45=53  A>  AG5B0 
 	 p u b l i c   b o o l e a n   w i t h d r a w a l   ( i n t   u s e r I d ,   d o u b l e   m o n e y )   {  
 	 	 i f ( m o n e y   <   0 )   {  
 	 	 	 r e t u r n   f a l s e ;  
 	 	 }  
 	 	 t r y   {  
 	 	 	 / / @>25@:0  =0  =0;8G85  :;85=B0,   C  :>B>@>3>  A=8<05<  45=L38 
 	 	 	 R e s u l t S e t   h a s U s e r   =   ( R e s u l t S e t )   e x e c u t e ( " s e l e c t " ,  
 	 	 	 " s e l e c t   u s e r _ i d "  
 	 	 	 +   "   f r o m   u s e r s   w h e r e   u s e r _ i d   =   "   +   u s e r I d   +   " ; "   ) ;  
 	 	 	 i f   ( ! h a s U s e r . f i r s t ( ) )   {  
 	 	 	 	 r e t u r n   f a l s e ;  
 	 	 	 }  
 	 	 	 / / @>25@:0  4>AB0B>G=>3>  :>;8G5AB20  45=53 
 	 	 	 R e s u l t S e t   e n o u g h t M o n e y   =   ( R e s u l t S e t )   e x e c u t e ( " s e l e c t " ,  
 	 	 	 " s e l e c t   b a l a n c e   f r o m   u s e r s   w h e r e   u s e r _ i d   =   " +   u s e r I d   + " ; " ) ;  
 	 	 	 d o u b l e   b a l a n c e   =   0 d ;  
 	 	 	 w h i l e ( e n o u g h t M o n e y . n e x t ( ) )   {  
 	 	 	 	 b a l a n c e   =   e n o u g h t M o n e y . g e t D o u b l e ( 1 ) ;  
 	 	 	 }  
 	 	 	 e n o u g h t M o n e y . c l o s e ( ) ;  
 	 	 	 i f ( m o n e y   >   b a l a n c e )   {  
 	 	 	 	 r e t u r n   f a l s e ;  
 	 	 	 }  
 	 	 	 / / !=8<05<  45=L38 
 	 	 	 e x e c u t e ( " u p d a t e " ,   " u p d a t e   u s e r s   s e t   b a l a n c e   =   b a l a n c e   -   "   +   m o n e y  
 	 	 	 	 	 +   "   w h e r e   u s e r _ i d   =   "   +   u s e r I d   +   " ; " ) ;  
 	 	 	 r o u n d ( ) ;  
 	 	 	 r e t u r n   t r u e ;  
 	 	 }   c a t c h   ( S Q L E x c e p t i o n   e )   {  
 	 	 	 S y s t e m . o u t . p r i n t l n ( e . g e t M e s s a g e ( ) ) ;  
 	 	 	 r e t u r n   f a l s e ;  
 	 	 }  
 	 }  
  
 	 / / >?>;=5=85  AG5B0 
 	 p u b l i c   b o o l e a n   r e f i l l   ( i n t   u s e r I d ,   d o u b l e   m o n e y )   {  
 	 	 i f ( m o n e y   <   0 )   {  
 	 	 	 r e t u r n   f a l s e ;  
 	 	 }  
 	 	 t r y   {  
 	 	 	 / / @>25@:0  =0  =0;8G85  :;85=B0,   :>B>@><C  ?>?>;=O5<  AG5B 
 	 	 	 R e s u l t S e t   h a s U s e r   =   ( R e s u l t S e t )   e x e c u t e ( " s e l e c t " ,  
 	 	 	 " s e l e c t   u s e r _ i d "  
 	 	 	 +   "   f r o m   u s e r s   w h e r e   u s e r _ i d   =   "   +   u s e r I d   +   " ; "   ) ;  
 	 	 	 i f   ( ! h a s U s e r . f i r s t ( ) )   {  
 	 	 	 	 r e t u r n   f a l s e ;  
 	 	 	 }  
 	 	 	 h a s U s e r . c l o s e ( ) ;  
 	 	 	 / / K?>;=O5<  ?>?>;=5=85  AG5B0 
 	 	 	 e x e c u t e ( " u p d a t e " ,   " u p d a t e   u s e r s   s e t   b a l a n c e   =   b a l a n c e   +   "   +   m o n e y  
 	 	 	 	 	 +   "   w h e r e   u s e r _ i d   =   " +   u s e r I d   +   " ; " ) ;  
 	 	 	 r o u n d ( ) ;  
 	 	 	 r e t u r n   t r u e ;  
 	 	 }   c a t c h   ( S Q L E x c e p t i o n   e )   {  
 	 	 	 S y s t e m . o u t . p r i n t l n ( e . g e t M e s s a g e ( ) ) ;  
 	 	 	 r e t u r n   f a l s e ;  
 	 	 }  
 	 }  
  
 	 / / 5@52>4  45=53  <564C  :;85=B0<8 
 	 p u b l i c   b o o l e a n   t r a n s f e r ( i n t   u s e r I d F r o m ,   i n t   u s e r I d T o ,   d o u b l e   m o n e y )   {  
 	 	 i f ( m o n e y   <   0 )   {  
 	 	 	 r e t u r n   f a l s e ;  
 	 	 }  
 	 	 t r y   {  
 	 	 	 / / @>25@:0  =0  =0;8G85  :;85=B0  >B  :>B>@>3>  ?5@5405<  45=L38 
 	 	 	 / / >65B  2K1@0AK20BL  8A:;NG5=85  S Q L E x c e p t i o n  
 	 	 	 R e s u l t S e t   h a s U s e r F r o m   =   ( R e s u l t S e t )   e x e c u t e ( " s e l e c t " ,  
 	 	 	 " s e l e c t   u s e r _ i d   f r o m   u s e r s   w h e r e   u s e r _ i d   =   "   +   u s e r I d F r o m   +   " ; " ) ;  
 	 	 	 i f   ( ! h a s U s e r F r o m . f i r s t ( ) )   {  
 	 	 	 	 r e t u r n   f a l s e ;  
 	 	 	 }  
 	 	 	 h a s U s e r F r o m . c l o s e ( ) ;  
 	 	 	 / / @>25@:0  =0  =0;8G85  :;85=B0  :>B>@K9  ?>;CG05B  45=L38 
 	 	 	 / / >65B  2K1@0AK20BL  8A:;NG5=85  S Q L E x c e p t i o n  
 	 	 	 R e s u l t S e t   h a s U s e r T o   =   ( R e s u l t S e t )   e x e c u t e ( " s e l e c t " ,  
 	 	 	 " s e l e c t   u s e r _ i d   f r o m   u s e r s   w h e r e   u s e r _ i d   =   "   +   u s e r I d T o   +   " ; " ) ;  
 	 	 	 i f   ( ! h a s U s e r T o . f i r s t ( ) )   {  
 	 	 	 	 r e t u r n   f a l s e ;  
 	 	 	 }  
 	 	 	 h a s U s e r T o . c l o s e ( ) ;  
 	 	 	 / / @>25@:0  =0  4>AB0B>G=>5  :>;8G5AB2>  45=53  C  :;85=B0  :>B>@K9  ?5@52>48B  AC<<C 
 	 	 	 / / >65B  2K1@0AK20BL  8A:;NG5=85  S Q L E x c e p t i o n  
 	 	 	 R e s u l t S e t   e n o u g h t M o n e y   = ( R e s u l t S e t )   e x e c u t e ( " s e l e c t " ,  
 	 	 	 " s e l e c t   b a l a n c e   f r o m   u s e r s   w h e r e   u s e r _ i d   =   "   +   u s e r I d F r o m   +   " ; " ) ;  
 	 	 	 d o u b l e   b a l a n c e   =   0 d ;  
 	 	 	 w h i l e   ( e n o u g h t M o n e y . n e x t ( ) )   {  
 	 	 	 	 b a l a n c e   =   e n o u g h t M o n e y . g e t D o u b l e ( 1 ) ;  
 	 	 	 }  
 	 	 	 e n o u g h t M o n e y . c l o s e ( ) ;  
 	 	 	 i f   ( b a l a n c e   <   m o n e y )   {  
 	 	 	 	 r e t u r n   f a l s e ;  
 	 	 	 }  
 	 	 	 / / 5@52>4  45=53  A  >4=>3>  =0  4@C3>9  0::0C=B 
 	 	 	 / / >65B  2K1@0AK20BL  8A:;NG5=85  S Q L E x c e p t i o n  
 	 	 	 e x e c u t e ( " u p d a t e " ,   " u p d a t e   u s e r s   s e t   b a l a n c e   =   b a l a n c e   -   "   +   m o n e y  
 	 	 	 	 	 +   "   w h e r e   u s e r _ i d   =   " +   u s e r I d F r o m   +   " ; " ) ;  
 	 	 	 e x e c u t e ( " u p d a t e " ,   " u p d a t e   u s e r s   s e t   b a l a n c e   =   b a l a n c e   +   "   +   m o n e y  
 	 	 	 	 	 +   "   w h e r e   u s e r _ i d   =   " +   u s e r I d T o   +   " ; " ) ;  
 	 	 	 r o u n d ( ) ;  
 	 	 	 r e t u r n   t r u e ;  
 	 	 }   c a t c h   ( S Q L E x c e p t i o n   e )   {  
 	 	 	 S y s t e m . o u t . p r i n t l n ( e . g e t M e s s a g e ( ) ) ;  
 	 	 	 r e t u r n   f a l s e ;  
 	 	 }  
 	 }  
  
 	 p u b l i c   U s e r I n f o   g e t U s e r I n f o ( i n t   u s e r I d )   {  
 	 	 t r y   {  
 	 	 	 R e s u l t S e t   r e s u l t   =   ( R e s u l t S e t )   e x e c u t e ( " s e l e c t " ,  
 	 	 	 " s e l e c t   *   f r o m   u s e r s   w h e r e   u s e r _ i d   =   "   +   u s e r I d   +   " ; " ) ;  
 	 	 	 U s e r I n f o   c l i e n t   =   n e w   U s e r I n f o ( ) ;  
 	 	 	 w h i l e ( r e s u l t . n e x t ( ) )   {  
 	 	 	 	 c l i e n t . u s e r I d   =   r e s u l t . g e t I n t ( 1 ) ;  
 	 	 	 	 c l i e n t . b a l a n c e   =   r e s u l t . g e t D o u b l e ( 2 ) ;  
 	 	 	 	 c l i e n t . p i n   =   r e s u l t . g e t I n t ( 3 ) ;  
 	 	 	 	 c l i e n t . b i r t h D a t e   =   r e s u l t . g e t D a t e ( 4 ) ;  
 	 	 	 	 c l i e n t . u s e r L o g i n   =   r e s u l t . g e t S t r i n g ( 5 ) ;  
 	 	 	 	 c l i e n t . s e c r e t Q u e s t i o n   =   r e s u l t . g e t S t r i n g ( 6 ) ;  
 	 	 	 	 c l i e n t . s e c r e t A n s w e r   =   r e s u l t . g e t S t r i n g ( 7 ) ;  
 	 	 	 	 c l i e n t . s t a t u s   =   r e s u l t . g e t B o o l e a n ( 8 ) ;  
 	 	 	 }  
 	 	 	 r e s u l t . c l o s e ( ) ;  
 	 	 	 r e t u r n   c l i e n t ;  
 	 	 }   c a t c h   ( S Q L E x c e p t i o n   e )   {  
 	 	 	 S y s t e m . o u t . p r i n t l n ( e . g e t M e s s a g e ( ) ) ;  
 	 	 	 r e t u r n   n u l l ;  
 	 	 }  
 	 }  
  
 	 / / 0:@K205<  A>548=5=85  A  107>9  40==KE  4;O  ?@54>B2@0I5=8O  CB5G:8  ?0<OB8 
 	 p u b l i c   v o i d   c l o s e C o n n e c t i o n ( )   {  
 	 	 t r y   {  
 	 	 	 c o n n e c t i o n . c l o s e ( ) ;  
 	 	 	 s t a t e m e n t . c l o s e ( ) ;  
 	 	 }   c a t c h   ( S Q L E x c e p t i o n   e )   { }  
 	 }  
 }  
 