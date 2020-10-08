# ROXANNE

**ROXANNE** (**RO**s fle**X**ible **A**cti**N**g co**N**troll**E**r) is a ROS package aiming at facilitating the integration of Artificial Ingtelligence (AI) Automated Planning and Execution techniques with robotic platforms. This package specifically supports the development of ROS-based control architecture integrating AI-based planning and execution capabilities that rely on the timeline-based planning and execution paradigm.

## Context and Motivations

Current control techniques enable an effective and successful employment of robotic solutions in manufacturing in order to improve the efficiency of production systems. Robot controllers provide _functional layers_ that can be used to program and configure robots. They manage the execution of a number of fixed primitives or skills that allow a robot to operate/act in a particular working environment and support production processes. Usually _standard controllers_ synthesize static behaviors that force robot to perform fixed sets of tasks limiting their autonomy. Such robtos are neither capable of autonomously deciding the tasks to execute according to the current needs of the factory nor dynamically adapting their behaviors (e.g., task execution or configuration) to the observed state of the working environment.

Although reliable and effective, standard control techniques are _static_ and may limit the adaptability of robots to the evolving needs of modern production systems. Namely, standard approaches to industrial robot control are conservative and lead to rigid behaviors that are not much effective in dynamic environments, where high variability of tasks and production dynamics must be considered. 

Human-Robot Collaboration (HRC) scenarios are well suited examples where a high level of flexibility and adaptability is necessary to take advantages of the tight interaction between humans and robots at production level. The co-existence of robots and humans sharing a fence-less environment raises a number of issues that must be properly solved to achieve safety, riability and efficiency of HRC cells. The uncontrollable dynamics of human operators introduce a source of uncertainty robot controllers must take into account to cary out collaborative production processes in an effective way.

AI and plan-based control technologies can enhance standard robot controllers with the flexibility and the _cognitive capabilities_ needed to achieve the desired level of autonomy, reliability and adaptability. Automated planning and execution techniques allow a robot to dynamically synthesize a set of tasks to be carried out and dynamically adapt control policies to the observed state of the working environment like e.g., production objectives and constraints, or the behavior and status of human workers collaborating within the production processes. However, plan-based controllers usually require significant efforts to be configured and deployed to real-world applications. There is a lack of standards that can facilitate the integration of plan-based control techniques with existing robot controllers and theri deployment to real-world industrial contexts.

## Research Background 

The deisgn of autonomous agents whose behaviors must be planned to act in an effective (and intelligent) way, dealing with a variety of environments and with a diversity of tasks and events as well, entails a set of deliberative skills. Among others, both planning and execution in AI are relevant (F. Ingrand and M. Ghallab, 2017). 

Plan-based control architectures usually integrate deliberative and execution processes pursuing different approaches and relying on different formalisms or models. Imperative approaches exploit e.g., STRIPS operators combined to PRS (F. Ingrand et al. 1996). Stochastic approaches leverage learning to build control policies after exeperiences or teaching e.g., by means of Hierarchical MDP (M. Hauskrecht et al. 1998). More classical plan-based solutions pursue a temporal approach implemented in planning frameworks like e.g., T-REX (F. Py et al. 2010).

In this context, PLATINUm (A. Umbrico et al. 2017) realizes a planning and execution approach which pursues a _uniform representation_ of control problems leveraging the timeline-nbased formalism (M. Cialdea et al. 2016). PLATINUm leverages the experience of APSI-TRF (S. Fratini et al. 2011) in space mission contexts and has successfully applied in HRC manufacturing scenarios (M. Inaki et al. 2016). In this context, the capability of dealing with _temporal uncertainty_ and _uncontrolalbel dynamics_ of the environment in a flexible and reliable way has shown promising results (S. Pellegrinelli et al. 2017, M. Faroni et al. 2020). Experiemnts have shown the capability of properly coordinating human and robot activitites and robustly executing collaborative plans by dynamically adapting robot behaviors to the observerd (uncontrollable) behaviors of human operators. 

# Objectives

ROXANNE aims at creating a ROS compliatn framework facilitating the use of timeline-based planning and execution capabilities in industrial settings. Target users are either researchers and manufacturing companies that want to evaluate flexible task-level controllers to better support production processes. The objectives of the project are thus the following:
- **Facilitate industrial robot programming** by providing a general-purpose specification language to model operational and temporal constraints of production processes and the dynamics of working environment, human operators and robot behaviors.
- Realize a **ROS-integrated goal-oriented planning and execution system** capable of autonomously synthesize the set of tasks a robot must perform to achieve production goals. Minimize production interruptions by dynamically coordinating and adapting robot behaviors by taking into account _uncontrollable_ dynamics of a working environment.
- Preovide **process-driven modeling and robot-agnostic control capabilities** to support interoperability and integration with different robotic platforms. 
- Define an **interoperability communication protocol** characterizing events and information exchanged within the life-cycle of a dynamic task planning system. Such a protocol defines services and dependencies that are necessary for the effective integration of the task planner with robot controllers and realize an autonomous robot architecture.
- Enable and **facilitate the use of timeline-based control techniques** in real-world production contexts by leveraging a standard platform like ROS and an existing timeline-based framework called PLATINUm.

# Acknowledgment

ROXANNE has received funding from the European Union's Horizon 2020 research and innovation programme under the project ROSIN with the gran agreement No 732287. 

# References
- (F. Ingrand and M. Ghallab, 2017) F. Ingrand and M. Ghgallab "Deliberation for autonomous robots: A survey". Artificial Intelligence, vol 247, pp. 10-44. 2017.
- (F. Ingrand et al. 1996) F. Ingrand, R. Chatila, R. Alami and F. Robert "PRS: a high level supervision and control language for autonomous mobile robots". IEEE International Conference on Robotic and Automation. 1996.
- (M. Hauskrecht et al. 1998) M. Hauskrecht, N. Meuleau, L. P. Kealbling, T. Dean, C. Boutilier "Hierarchical solution of Markov decision processes using macro-actions". 1998.
- (F. Py et al. 2010)F. Py, K. Raja and C. McGann "A systematic agent framework for situated autonomous systems". 9th International Conference on Autonomous Agnets and Multiagent Systems (AAMAS). 2010.
- (A. Umbrico et al. 2017) A. Umbrico, M. Cialdea Mayer, A. Orlandini "PLATINUm: A framework for planning and acting". 16th International Conference of the Italian Association for Artificial Intelligence (AI*IA). 2017.
- (M. Cialdea et al. 2016) M. Cialdea Mayer, A. Orlandini, A. Umbrico "Planning and execution with flexible timelines: a formal account". Acta Informatica. 2016.
- (S. Fratini et al. 2011) S. Fratini, A. Cesta, R. De Benedictis, A. Orlandini, R. Rasconi "APSI-based deliberation in goal oriented autonomous controllers". ESA-ASTRA. 2011.
- (M. Inaki et al. 2016) M. Inaki, N. Pedrocchi, A. Orlandini, J. d. G. Fernandex, C. Vogel, A. Geenen, K. Althoefer, A. Shafti "FourByThree: Imagine humans and robots working hand in hand". IEEE International Conference on Emerging Technologies and Factory Automation (ETFA). 2016.
- (S. Pellegrinelli et al. 2017) S. Pellegrinelli, A. Orlandini, N. Pedrocchi, A. Umbrico, T. Tolio "Motion planning and scheduling for human and industrial-robot collaboration". CIRP Annals, vol. 66, pp. 1-4. 2017.
- (M. Faroni et al. 2020) M. Faroni, M. Beschi, S. Ghidini, N. Pedrocchi, A. Umbrico, A. Orlandini, A. Cesta "A layered control approach to human-aware task and motion planning for Human-Robot Collaboration". 29th IEEE International Conference on Robot and Human Interactive Communication (RO-MAN). 2020.
