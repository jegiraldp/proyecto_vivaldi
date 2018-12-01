;;jVivaldi - 2006 Dominio-(Creado din�micamente)
(define (domain dominio)
(:requirements :adl)
(:predicates
(conocido nombreLibro)
(conocido codigoPersona)
(conocido nombrePersona)
(and(conocido nombreLibro) (conocido nombrePersona))
(reservado nombreLibro)
)
(:action LibroProcess
:parameters (?obj)
:precondition
(conocido codigoLibro)
:effect(conocido nombreLibro))

(:action PersonasProcess
:parameters (?obj)
:precondition
(conocido codigoPersona)
:effect(conocido nombrePersona))

(:action LibrosReservaProcess
:parameters (?obj)
:precondition
(and(conocido nombreLibro) (conocido nombrePersona))
:effect(reservado nombreLibro))
)
